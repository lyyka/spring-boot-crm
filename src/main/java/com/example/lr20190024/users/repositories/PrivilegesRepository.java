package com.example.lr20190024.users.repositories;

import com.example.lr20190024.users.entities.Privilege;
import com.example.lr20190024.users.enums.PrivilegeName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PrivilegesRepository extends JpaRepository<Privilege, Long> {
    List<Privilege> findByNameIn(Collection<PrivilegeName> names);
}
