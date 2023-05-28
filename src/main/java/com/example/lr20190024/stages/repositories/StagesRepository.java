package com.example.lr20190024.stages.repositories;

import com.example.lr20190024.pipelines.entities.Pipeline;
import com.example.lr20190024.stages.entities.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface StagesRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByPipeline(Pipeline pipeline);

    @Query(value = "SELECT s FROM Stage s JOIN FETCH s.pipeline")
    Set<Stage> fetchAllWithPipelines();
}
