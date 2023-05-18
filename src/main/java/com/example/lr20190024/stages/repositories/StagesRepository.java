package com.example.lr20190024.stages.repositories;

import com.example.lr20190024.stages.entities.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StagesRepository extends JpaRepository<Stage, Long> {
}
