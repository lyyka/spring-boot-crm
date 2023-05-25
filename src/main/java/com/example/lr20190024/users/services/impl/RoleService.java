package com.example.lr20190024.users.services.impl;

import com.example.lr20190024.users.repositories.RolesRepository;
import com.example.lr20190024.users.responses.RoleResponse;
import com.example.lr20190024.users.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RolesRepository rolesRepository;

    public List<RoleResponse> all() {
        return this.rolesRepository.findAll().stream().map(RoleResponse::fromEntity).toList();
    }
}
