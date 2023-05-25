package com.example.lr20190024.users.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStoreRequest {
    @Email
    private String email;

    private Boolean enabled;

    private String firstName;

    private String lastName;

    @Positive
    private Long roleId;
}
