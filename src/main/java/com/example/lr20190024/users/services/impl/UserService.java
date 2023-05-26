package com.example.lr20190024.users.services.impl;

import com.example.lr20190024.common.emails.IEmailService;
import com.example.lr20190024.common.exception.ResourceNotFoundException;
import com.example.lr20190024.common.utils.RandomPassword;
import com.example.lr20190024.users.emails.UserCreatedEmail;
import com.example.lr20190024.users.entities.User;
import com.example.lr20190024.users.exceptions.SelfUpdateException;
import com.example.lr20190024.users.repositories.RolesRepository;
import com.example.lr20190024.users.repositories.UsersRepository;
import com.example.lr20190024.users.requests.UserStoreRequest;
import com.example.lr20190024.users.responses.UserResponse;
import com.example.lr20190024.users.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final RandomPassword randomPassword;
    private final IEmailService emailService;

    public Page<UserResponse> all(Pageable pageable) {
        Page<User> page = this.usersRepository.findAll(pageable);
        return new PageImpl<>(
                page.getContent().stream().map(UserResponse::fromEntity).toList(),
                pageable,
                page.getTotalElements()
        );
    }

    public UserResponse get(Long id) {
        return UserResponse.fromEntity(this.usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found")));
    }

    public UserResponse store(UserStoreRequest request) {
        String password = randomPassword.generate();
        User user = this.usersRepository.save(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        this.passwordEncoder.encode(password),
                        request.getEnabled(),
                        rolesRepository.findById(request.getRoleId().longValue()).orElseThrow(() -> new ResourceNotFoundException("Role not found"))
                )
        );
        emailService.to(new String[]{request.getEmail()}).send(
                new UserCreatedEmail(
                        request.getEmail(),
                        password
                )
        );
        return UserResponse.fromEntity(user);
    }

    public UserResponse update(Long id, UserStoreRequest request) throws SelfUpdateException {
        Authentication currentAuthUser = SecurityContextHolder.getContext().getAuthentication();
        User currentAuthUserDb = this.usersRepository.findByEmail(currentAuthUser.getName());

        if (currentAuthUserDb != null && Objects.equals(currentAuthUserDb.getId(), id)) {
            throw new SelfUpdateException("Cannot self update an account");
        }

        User user = this.usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setEnabled(request.getEnabled());
        user.setRole(rolesRepository.findById(request.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("Role not found")));
        return UserResponse.fromEntity(this.usersRepository.save(user));
    }

    public void delete(Long id) {
        this.usersRepository.deleteById(id);
    }
}
