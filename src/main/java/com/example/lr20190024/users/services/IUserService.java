package com.example.lr20190024.users.services;

import com.example.lr20190024.users.exceptions.SelfUpdateException;
import com.example.lr20190024.users.requests.UserStoreRequest;
import com.example.lr20190024.users.responses.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<UserResponse> all(Pageable pageable);

    UserResponse get(Long id);

    UserResponse store(UserStoreRequest request);

    UserResponse update(Long id, UserStoreRequest request) throws SelfUpdateException;

    void delete(Long id);
}
