package com.example.lr20190024.users.repositories;

import com.example.lr20190024.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
