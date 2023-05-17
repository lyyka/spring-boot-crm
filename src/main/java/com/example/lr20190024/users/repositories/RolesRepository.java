package com.example.lr20190024.users.repositories;

import com.example.lr20190024.users.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
}
