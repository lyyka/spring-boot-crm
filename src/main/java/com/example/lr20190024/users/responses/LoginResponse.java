package com.example.lr20190024.users.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String plainUsername;
    private String encryptedUsername;
    private List<String> authorities;
}
