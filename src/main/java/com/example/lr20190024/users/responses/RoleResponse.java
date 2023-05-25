package com.example.lr20190024.users.responses;

import com.example.lr20190024.users.entities.Role;
import com.example.lr20190024.users.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleResponse {
    private Long id;
    private RoleName name;

    public static RoleResponse fromEntity(Role role) {
        return new RoleResponse(
                role.getId(),
                role.getName()
        );
    }
}
