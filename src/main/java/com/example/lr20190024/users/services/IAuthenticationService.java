package com.example.lr20190024.users.services;

public interface IAuthenticationService {
    void authenticate(String username, String password) throws Exception;
}
