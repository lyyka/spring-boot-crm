package com.example.lr20190024.deals.repositories;

import com.example.lr20190024.deals.entities.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealsRepository extends JpaRepository<Deal, Long> {
}
