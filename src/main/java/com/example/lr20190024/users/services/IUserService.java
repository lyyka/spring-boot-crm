package com.example.lr20190024.users.services;

import com.example.lr20190024.users.entities.User;
import com.example.lr20190024.users.requests.UserStoreRequest;

import java.util.List;

public interface IUserService {
    List<User> all();

    User store(UserStoreRequest request);

    User update(Long id, UserStoreRequest request);

    void delete(Long id);
}
