package com.example.lr20190024.users.services.impl;

import com.example.lr20190024.users.entities.Privilege;
import com.example.lr20190024.users.entities.Role;
import com.example.lr20190024.users.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.lr20190024.users.entities.User dbUser = usersRepository.findByEmail(username);
        if (dbUser == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        /*
            Map all user roles into authorities.
            For example, `ADMIN` role, with authority `CREATE_PIPELINE`,
            will result in a `ROLE_ADMIN_CREATE_PIPELINE` authority.
         */
        Role role = dbUser.getRole();
        for (Privilege privilege : role.getPrivileges()) {
            authorityList.add(
                    new SimpleGrantedAuthority("ROLE_" + role.getName() + "_" + privilege.getName())
            );
        }

        return new User(dbUser.getUsername(), dbUser.getPassword(), authorityList);
    }
}
