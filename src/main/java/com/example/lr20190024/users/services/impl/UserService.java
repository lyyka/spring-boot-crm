package com.example.lr20190024.users.services.impl;

import com.example.lr20190024.common.emails.IEmailService;
import com.example.lr20190024.common.exception.ResourceNotFoundException;
import com.example.lr20190024.common.utils.RandomPassword;
import com.example.lr20190024.users.emails.UserCreatedEmail;
import com.example.lr20190024.users.entities.User;
import com.example.lr20190024.users.repositories.RolesRepository;
import com.example.lr20190024.users.repositories.UsersRepository;
import com.example.lr20190024.users.requests.UserStoreRequest;
import com.example.lr20190024.users.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final RandomPassword randomPassword;
    private final IEmailService emailService;

    public List<User> all() {
        return this.usersRepository.findAll();
    }

    public User store(UserStoreRequest request) {
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
        return user;
    }

    public User update(Long id, UserStoreRequest request) {
        User user = this.usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setEnabled(request.getEnabled());
        user.setRole(rolesRepository.findById(request.getRoleId().longValue()).orElseThrow(() -> new ResourceNotFoundException("Role not found")));
        return this.usersRepository.save(user);
    }

    public void delete(Long id) {
        this.usersRepository.deleteById(id);
    }
}
