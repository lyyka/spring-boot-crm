package com.example.lr20190024.clients.services.impl;

import com.example.lr20190024.clients.entities.Client;
import com.example.lr20190024.clients.repositories.ClientsRepository;
import com.example.lr20190024.clients.requests.ClientStoreRequest;
import com.example.lr20190024.clients.services.IClientsService;
import com.example.lr20190024.common.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientsService implements IClientsService {

    private final ClientsRepository clientsRepository;

    @Override
    public List<Client> getAll() {
        return this.clientsRepository.findAll();
    }

    @Override
    public Client store(ClientStoreRequest clientStoreRequest) {
        return this.clientsRepository.save(clientStoreRequest.toEntity());
    }

    @Override
    public Client update(Long id, ClientStoreRequest clientStoreRequest) throws ResourceNotFoundException {
        Client toUpdate = this.clientsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No client found"));
        toUpdate.setFirstName(clientStoreRequest.getFirstName());
        toUpdate.setLastName(clientStoreRequest.getFirstName());
        toUpdate.setEmail(clientStoreRequest.getFirstName());
        toUpdate.setPhoneNumber(clientStoreRequest.getFirstName());
        return this.clientsRepository.save(toUpdate);
    }

    @Override
    public void delete(Long id) {
        this.clientsRepository.deleteById(id);
    }
}
