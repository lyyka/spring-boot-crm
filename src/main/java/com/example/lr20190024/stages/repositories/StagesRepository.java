package com.example.lr20190024.stages.repositories;

import com.example.lr20190024.pipelines.entities.Pipeline;
import com.example.lr20190024.stages.entities.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StagesRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByPipeline(Pipeline pipeline);
}
