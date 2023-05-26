package com.example.lr20190024.deals.repositories;

import com.example.lr20190024.deals.entities.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealsRepository extends JpaRepository<Deal, Long> {
    List<Deal> findByClientId(Long clientId);
}
