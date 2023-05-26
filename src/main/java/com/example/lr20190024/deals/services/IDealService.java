package com.example.lr20190024.deals.services;

import com.example.lr20190024.deals.requests.DealStoreRequest;
import com.example.lr20190024.deals.requests.DealUpdateRequest;
import com.example.lr20190024.deals.responses.DealResponse;

import java.util.List;

public interface IDealService {
    List<DealResponse> getForClient(Long clientId);

    DealResponse store(DealStoreRequest request);

    DealResponse update(Long id, DealUpdateRequest request);

    void delete(Long id);
}
