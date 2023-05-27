package com.example.lr20190024.clients.services;

import com.example.lr20190024.clients.requests.ClientStoreRequest;
import com.example.lr20190024.clients.responses.ClientResponse;
import com.example.lr20190024.clients.services.filters.ClientIndexFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClientsService {
    Page<ClientResponse> getAll(ClientIndexFilterRequest clientIndexFilterRequest, Pageable pageable);

    ClientResponse get(Long id);

    ClientResponse store(ClientStoreRequest clientStoreRequest);

    ClientResponse update(Long id, ClientStoreRequest clientStoreRequest);

    void delete(Long id);
}
