package com.example.lr20190024.pipelines.repositories;

import com.example.lr20190024.pipelines.entities.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PipelinesRepository extends JpaRepository<Pipeline, Long> {
}
