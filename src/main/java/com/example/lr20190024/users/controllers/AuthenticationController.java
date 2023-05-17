package com.example.lr20190024.users.controllers;

import com.example.lr20190024.config.JwtTokenUtil;
import com.example.lr20190024.users.requests.LoginRequest;
import com.example.lr20190024.users.responses.LoginResponse;
import com.example.lr20190024.users.services.AuthenticationService;
import com.example.lr20190024.users.services.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody @Validated LoginRequest loginRequest) {
        try {
            authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new LoginResponse(null));
        }

        try {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(loginRequest.getEmail());

            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new LoginResponse(token));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(new LoginResponse(null));
        }
    }
}
