package com.example.lr20190024.users.responses;

import com.example.lr20190024.users.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private RoleResponse role;

    public static UserResponse fromEntity(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.isEnabled(),
                RoleResponse.fromEntity(user.getRole())
        );
    }
}
