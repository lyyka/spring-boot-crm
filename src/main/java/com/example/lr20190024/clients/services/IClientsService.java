package com.example.lr20190024.clients.services;

import com.example.lr20190024.clients.entities.Client;
import com.example.lr20190024.clients.requests.ClientStoreRequest;

import java.util.List;

public interface IClientsService {
    List<Client> getAll();

    Client store(ClientStoreRequest clientStoreRequest);

    Client update(Long id, ClientStoreRequest clientStoreRequest);

    void delete(Long id);
}
