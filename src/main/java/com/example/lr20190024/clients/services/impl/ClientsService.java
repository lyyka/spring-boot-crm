package com.example.lr20190024.clients.services.impl;

import com.example.lr20190024.clients.entities.Client;
import com.example.lr20190024.clients.repositories.ClientsRepository;
import com.example.lr20190024.clients.requests.ClientStoreRequest;
import com.example.lr20190024.clients.responses.ClientResponse;
import com.example.lr20190024.clients.services.IClientsService;
import com.example.lr20190024.clients.services.filters.ClientIndexFilterRequest;
import com.example.lr20190024.common.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientsService implements IClientsService {

    private final ClientsRepository clientsRepository;

    @Override
    public Page<ClientResponse> getAll(ClientIndexFilterRequest clientIndexFilterRequest, Pageable pageable) {
        Page<Client> page = this.clientsRepository.findAll(clientIndexFilterRequest.getSearchSpecification(), pageable);
        return new PageImpl<>(
                page.getContent().stream().map(ClientResponse::fromEntity).toList(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public ClientResponse get(Long id) {
        return ClientResponse.fromEntity(this.clientsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found")));
    }

    @Override
    public ClientResponse store(ClientStoreRequest clientStoreRequest) {
        return ClientResponse.fromEntity(this.clientsRepository.save(clientStoreRequest.toEntity()));
    }

    @Override
    public ClientResponse update(Long id, ClientStoreRequest clientStoreRequest) throws ResourceNotFoundException {
        Client toUpdate = this.clientsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No client found"));
        toUpdate.setFirstName(clientStoreRequest.getFirstName());
        toUpdate.setLastName(clientStoreRequest.getLastName());
        toUpdate.setEmail(clientStoreRequest.getEmail());
        toUpdate.setPhoneNumber(clientStoreRequest.getPhoneNumber());
        return ClientResponse.fromEntity(this.clientsRepository.save(toUpdate));
    }

    @Override
    public void delete(Long id) {
        this.clientsRepository.deleteById(id);
    }
}
