package com.example.lr20190024.clients.controllers;

import com.example.lr20190024.clients.entities.Client;
import com.example.lr20190024.clients.requests.ClientStoreRequest;
import com.example.lr20190024.clients.services.IClientsService;
import com.example.lr20190024.common.exception.ResourceNotFoundException;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientsController {
    private final IClientsService clientsService;

    @GetMapping(value = "")
    public ResponseEntity<List<Client>> index() {
        return ResponseEntity.ok().body(this.clientsService.getAll());
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> store(@RequestBody @Validated ClientStoreRequest clientStoreRequest) {
        Client client = this.clientsService.store(clientStoreRequest);
        return ResponseEntity.ok().body(client);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> update(@PathVariable @Positive Long id, @RequestBody @Validated ClientStoreRequest clientStoreRequest) {
        try {
            Client client = this.clientsService.update(id, clientStoreRequest);
            return ResponseEntity.ok().body(client);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        this.clientsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
