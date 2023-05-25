package com.example.lr20190024.clients.services;

import com.example.lr20190024.clients.requests.ClientStoreRequest;
import com.example.lr20190024.clients.responses.ClientResponse;

import java.util.List;

public interface IClientsService {
    List<ClientResponse> getAll();

    ClientResponse get(Long id);

    ClientResponse store(ClientStoreRequest clientStoreRequest);

    ClientResponse update(Long id, ClientStoreRequest clientStoreRequest);

    void delete(Long id);
}
