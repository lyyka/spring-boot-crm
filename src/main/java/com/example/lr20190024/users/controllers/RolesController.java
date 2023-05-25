package com.example.lr20190024.users.controllers;

import com.example.lr20190024.users.responses.RoleResponse;
import com.example.lr20190024.users.services.IRoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "crm")
@RequestMapping("/api/roles")
public class RolesController {
    private final IRoleService roleService;

    @GetMapping(value = "")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_VIEW_USER')")
    public ResponseEntity<List<RoleResponse>> index() {
        return ResponseEntity.ok().body(this.roleService.all());
    }
}
