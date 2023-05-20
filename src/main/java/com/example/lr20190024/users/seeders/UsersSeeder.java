package com.example.lr20190024.users.seeders;

import com.example.lr20190024.users.entities.Privilege;
import com.example.lr20190024.users.entities.Role;
import com.example.lr20190024.users.entities.User;
import com.example.lr20190024.users.enums.PrivilegeName;
import com.example.lr20190024.users.enums.RoleName;
import com.example.lr20190024.users.repositories.PrivilegesRepository;
import com.example.lr20190024.users.repositories.RolesRepository;
import com.example.lr20190024.users.repositories.UsersRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UsersSeeder {
    private UsersRepository usersRepository;
    private RolesRepository rolesRepository;
    private PrivilegesRepository privilegesRepository;
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seed() {
        // save all privileges
        List<Privilege> privileges = new ArrayList<>();
        for (PrivilegeName privilegeName : PrivilegeName.values()) {
            privileges.add(new Privilege(privilegeName));
        }
        privilegesRepository.saveAll(privileges);

        // setup admin role
        Set<Privilege> adminPrivileges = new HashSet<>(privilegesRepository.findAll());
        Role adminRole = rolesRepository.save(new Role(RoleName.ADMIN, adminPrivileges));

        // setup user role
        List<PrivilegeName> userPrivilegesCollection = new ArrayList<>();
        userPrivilegesCollection.add(PrivilegeName.CREATE_CLIENT);
        userPrivilegesCollection.add(PrivilegeName.VIEW_CLIENT);
        userPrivilegesCollection.add(PrivilegeName.STORE_CLIENT);
        userPrivilegesCollection.add(PrivilegeName.UPDATE_CLIENT);
        userPrivilegesCollection.add(PrivilegeName.DELETE_CLIENT);
        userPrivilegesCollection.add(PrivilegeName.VIEW_PIPELINE);
        userPrivilegesCollection.add(PrivilegeName.VIEW_STAGE);
        userPrivilegesCollection.add(PrivilegeName.CREATE_DEAL);
        userPrivilegesCollection.add(PrivilegeName.VIEW_DEAL);
        userPrivilegesCollection.add(PrivilegeName.STORE_DEAL);
        userPrivilegesCollection.add(PrivilegeName.UPDATE_DEAL);
        userPrivilegesCollection.add(PrivilegeName.CREATE_ACTIVITY);
        userPrivilegesCollection.add(PrivilegeName.VIEW_ACTIVITY);
        userPrivilegesCollection.add(PrivilegeName.STORE_ACTIVITY);
        userPrivilegesCollection.add(PrivilegeName.UPDATE_ACTIVITY);
        userPrivilegesCollection.add(PrivilegeName.DELETE_ACTIVITY);
        userPrivilegesCollection.add(PrivilegeName.CREATE_NOTE);
        userPrivilegesCollection.add(PrivilegeName.VIEW_NOTE);
        userPrivilegesCollection.add(PrivilegeName.STORE_NOTE);
        userPrivilegesCollection.add(PrivilegeName.UPDATE_NOTE);
        userPrivilegesCollection.add(PrivilegeName.DELETE_NOTE);

        Set<Privilege> userPrivileges = new HashSet<>(privilegesRepository.findByNameIn(userPrivilegesCollection));
        Role userRole = rolesRepository.save(new Role(RoleName.USER, userPrivileges));

        User crmAdmin = new User();
        crmAdmin.setEmail("crmadmin@gmail.com");
        crmAdmin.setPassword(passwordEncoder.encode("crm2022"));
        crmAdmin.setEnabled(true);
        crmAdmin.setFirstName("CRM");
        crmAdmin.setLastName("Admin");
        crmAdmin.setRole(adminRole);
        usersRepository.save(crmAdmin);

        User crmUser = new User();
        crmUser.setEmail("crmuser@gmail.com");
        crmUser.setPassword(passwordEncoder.encode("crm2022"));
        crmUser.setEnabled(true);
        crmUser.setFirstName("CRM");
        crmUser.setLastName("User");
        crmUser.setRole(userRole);
        usersRepository.save(crmUser);
    }
}
