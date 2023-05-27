package com.example.lr20190024.clients.controllers;

import com.example.lr20190024.clients.requests.ClientStoreRequest;
import com.example.lr20190024.clients.responses.ClientResponse;
import com.example.lr20190024.clients.services.IClientsService;
import com.example.lr20190024.clients.services.filters.ClientIndexFilterRequest;
import com.example.lr20190024.common.exception.ResourceNotFoundException;
import com.example.lr20190024.common.requests.PaginateRequest;
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
@RequestMapping("/api/clients")
public class ClientsController {
    private final IClientsService clientsService;

    @GetMapping(value = "")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_VIEW_CLIENT', 'ROLE_USER_VIEW_CLIENT')")
    public ResponseEntity<Page<ClientResponse>> index(@Valid ClientIndexFilterRequest clientIndexFilterRequest, @Valid PaginateRequest paginateRequest) {
        return ResponseEntity.ok().body(this.clientsService.getAll(
                clientIndexFilterRequest,
                paginateRequest.getPageable()
        ));
    }

    @GetMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_VIEW_CLIENT', 'ROLE_USER_VIEW_CLIENT')")
    public ResponseEntity<ClientResponse> get(@PathVariable @Positive Long id) {
        return ResponseEntity.ok().body(this.clientsService.get(id));
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_CREATE_CLIENT', 'ROLE_USER_CREATE_CLIENT')")
    public ResponseEntity<ClientResponse> store(@RequestBody @Validated ClientStoreRequest clientStoreRequest) {
        return ResponseEntity.ok().body(this.clientsService.store(clientStoreRequest));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_UPDATE_CLIENT', 'ROLE_USER_UPDATE_CLIENT')")
    public ResponseEntity<ClientResponse> update(@PathVariable @Positive Long id, @RequestBody @Validated ClientStoreRequest clientStoreRequest) {
        try {
            return ResponseEntity.ok().body(this.clientsService.update(id, clientStoreRequest));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_DELETE_CLIENT', 'ROLE_USER_DELETE_CLIENT')")
    public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        this.clientsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
