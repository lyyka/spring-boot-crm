package com.example.lr20190024.users.controllers;

import com.example.lr20190024.common.requests.PaginateRequest;
import com.example.lr20190024.users.exceptions.SelfUpdateException;
import com.example.lr20190024.users.requests.UserStoreRequest;
import com.example.lr20190024.users.responses.UserResponse;
import com.example.lr20190024.users.services.IUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "crm")
@RequestMapping("/api/users")
public class UsersController {
    private final IUserService userService;

    @GetMapping(value = "")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_VIEW_USER')")
    public ResponseEntity<Page<UserResponse>> index(@Valid PaginateRequest paginateRequest) {
        return ResponseEntity.ok().body(this.userService.all(
                paginateRequest.getPageable()
        ));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_VIEW_USER')")
    public ResponseEntity<UserResponse> get(@PathVariable @Positive Long id) {
        return ResponseEntity.ok().body(this.userService.get(id));
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CREATE_USER')")
    public ResponseEntity<UserResponse> store(@RequestBody @Validated UserStoreRequest request) {
        return ResponseEntity.ok().body(
                this.userService.store(request)
        );
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_UPDATE_USER')")
    public ResponseEntity<UserResponse> update(@PathVariable @Positive Long id, @RequestBody @Validated UserStoreRequest request) {
        try {
            return ResponseEntity.ok().body(
                    this.userService.update(id, request)
            );
        } catch (SelfUpdateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_DELETE_USER')")
    public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        this.userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
