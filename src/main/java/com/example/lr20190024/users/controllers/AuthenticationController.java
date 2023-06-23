package com.example.lr20190024.users.controllers;

import com.example.lr20190024.config.JwtTokenUtil;
import com.example.lr20190024.users.requests.LoginRequest;
import com.example.lr20190024.users.responses.LoginResponse;
import com.example.lr20190024.users.services.IAuthenticationService;
import com.example.lr20190024.users.services.impl.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody @Validated LoginRequest loginRequest) {
        try {
            authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new LoginResponse(null, null, null, null));
        }

        try {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(loginRequest.getEmail());

            final String token = jwtTokenUtil.generateToken(userDetails);

            String plainTextUsername = userDetails.getUsername();
            String encryptedUsername = Encryptors.text(userDetails.getPassword(), KeyGenerators.string().generateKey())
                    .encrypt(plainTextUsername);
            return ResponseEntity.ok(new LoginResponse(token, plainTextUsername, encryptedUsername, userDetails.getAuthorities().stream().map((GrantedAuthority auth) -> auth.getAuthority()).toList()));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(new LoginResponse(null, null, null, null));
        }
    }
}
