package com.example.lr20190024.clients.controllers;

import com.example.lr20190024.clients.entities.Client;
import com.example.lr20190024.clients.requests.ClientStoreRequest;
import com.example.lr20190024.clients.services.IClientsService;
import com.example.lr20190024.common.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "crm")
@RequestMapping("/api/clients")
public class ClientsController {
    private final IClientsService clientsService;

    @GetMapping(value = "")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_VIEW_CLIENT', 'ROLE_USER_VIEW_CLIENT')")
    public ResponseEntity<List<Client>> index() {
        return ResponseEntity.ok().body(this.clientsService.getAll());
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_CREATE_CLIENT', 'ROLE_USER_CREATE_CLIENT')")
    public ResponseEntity<Client> store(@RequestBody @Validated ClientStoreRequest clientStoreRequest) {
        Client client = this.clientsService.store(clientStoreRequest);
        return ResponseEntity.ok().body(client);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_UPDATE_CLIENT', 'ROLE_USER_UPDATE_CLIENT')")
    public ResponseEntity<Client> update(@PathVariable @Positive Long id, @RequestBody @Validated ClientStoreRequest clientStoreRequest) {
        try {
            Client client = this.clientsService.update(id, clientStoreRequest);
            return ResponseEntity.ok().body(client);
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
