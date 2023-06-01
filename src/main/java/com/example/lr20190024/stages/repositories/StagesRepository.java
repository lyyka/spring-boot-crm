package com.example.lr20190024.stages.repositories;

import com.example.lr20190024.pipelines.entities.Pipeline;
import com.example.lr20190024.stages.entities.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface StagesRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByPipelineOrderByStageOrderAsc(Pipeline pipeline);

    @Modifying
    @Query("update Stage s " +
            "set s.stageOrder = s.stageOrder - 1 " +
            "where s.pipeline = :inPipeline and s.stageOrder >= :greaterThanOrEqualThanOrder")
    void decrementAfterOrder(Integer greaterThanOrEqualThanOrder, Pipeline inPipeline);

    @Modifying
    @Query("update Stage s " +
            "set s.stageOrder = s.stageOrder + 1 " +
            "where s.pipeline = :inPipeline and s.stageOrder >= :greaterThanOrEqualThanOrder")
    void incrementAfterOrder(Integer greaterThanOrEqualThanOrder, Pipeline inPipeline);

    @Query(value = "SELECT s FROM Stage s JOIN FETCH s.pipeline order by s.stageOrder asc")
    Set<Stage> fetchAllWithPipelines();

    @Query(value = "select max(s.stageOrder) from Stage s where s.pipeline = :pipeline")
    Integer getMaxOrderInPipeline(Pipeline pipeline);
}
