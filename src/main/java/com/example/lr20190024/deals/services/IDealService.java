package com.example.lr20190024.deals.services;

import com.example.lr20190024.deals.entities.Deal;
import com.example.lr20190024.deals.requests.DealStoreRequest;
import com.example.lr20190024.deals.requests.DealUpdateRequest;

import java.util.List;

public interface IDealService {
    List<Deal> all();

    Deal store(DealStoreRequest request);

    Deal update(Long id, DealUpdateRequest request);

    void delete(Long id);
}
