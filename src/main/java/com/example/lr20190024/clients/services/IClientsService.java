package com.example.lr20190024.clients.services;

import com.example.lr20190024.clients.requests.ClientStoreRequest;
import com.example.lr20190024.clients.responses.ClientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClientsService {
    Page<ClientResponse> getAll(Pageable pageable);

    ClientResponse get(Long id);

    ClientResponse store(ClientStoreRequest clientStoreRequest);

    ClientResponse update(Long id, ClientStoreRequest clientStoreRequest);

    void delete(Long id);
}
