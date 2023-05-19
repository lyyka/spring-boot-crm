package com.example.lr20190024.users.controllers;

import com.example.lr20190024.users.entities.User;
import com.example.lr20190024.users.requests.UserStoreRequest;
import com.example.lr20190024.users.services.IUserService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
    private final IUserService userService;

    @GetMapping(value = "")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_VIEW_USER')")
    public ResponseEntity<List<User>> index() {
        return ResponseEntity.ok().body(this.userService.all());
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CREATE_USER')")
    public ResponseEntity<User> store(@RequestBody @Validated UserStoreRequest request) {
        return ResponseEntity.ok().body(
                this.userService.store(request)
        );
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_UPDATE_USER')")
    public ResponseEntity<User> update(@PathVariable @Positive Long id, @RequestBody @Validated UserStoreRequest request) {
        return ResponseEntity.ok().body(
                this.userService.update(id, request)
        );
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_DELETE_USER')")
    public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        this.userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
