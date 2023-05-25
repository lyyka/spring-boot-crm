package com.example.lr20190024.users.services;

import com.example.lr20190024.users.requests.UserStoreRequest;
import com.example.lr20190024.users.responses.UserResponse;

import java.util.List;

public interface IUserService {
    List<UserResponse> all();

    UserResponse store(UserStoreRequest request);

    UserResponse update(Long id, UserStoreRequest request);

    void delete(Long id);
}
