package com.example.lr20190024.deals.repositories;

import com.example.lr20190024.deals.entities.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DealsRepository extends JpaRepository<Deal, Long> {
    @Query(value = "SELECT d FROM Deal d JOIN FETCH d.stage.pipeline WHERE d.client.id = :clientId")
    List<Deal> findByClientId(Long clientId);
}
