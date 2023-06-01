package com.example.lr20190024.stages.services.impl;

import com.example.lr20190024.common.exception.ResourceNotFoundException;
import com.example.lr20190024.pipelines.entities.Pipeline;
import com.example.lr20190024.pipelines.repositories.PipelinesRepository;
import com.example.lr20190024.stages.entities.Stage;
import com.example.lr20190024.stages.exceptions.InvalidOrderException;
import com.example.lr20190024.stages.repositories.StagesRepository;
import com.example.lr20190024.stages.requests.StageOrderUpdateRequest;
import com.example.lr20190024.stages.requests.StageStoreRequest;
import com.example.lr20190024.stages.requests.StageUpdateRequest;
import com.example.lr20190024.stages.responses.StageResponse;
import com.example.lr20190024.stages.services.IStageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StageService implements IStageService {
    private final StagesRepository stagesRepository;
    private final PipelinesRepository pipelinesRepository;

    @Override
    public List<StageResponse> getForPipeline(Long id) {
        return this.stagesRepository.findByPipelineOrderByStageOrderAsc(
                this.pipelinesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pipeline not found"))
        ).stream().map(StageResponse::fromEntity).toList();
    }

    @Override
    public StageResponse get(Long id) {
        return StageResponse.fromEntity(
                this.stagesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stage not found"))
        );
    }

    @Override
    public Map<Long, List<StageResponse>> getStagesPerPipeline() {
        Set<Stage> allStages = this.stagesRepository.fetchAllWithPipelines();
        Map<Long, List<StageResponse>> result = new HashMap<>();

        for (Stage stage : allStages) {
            List<StageResponse> idsList = result.get(stage.getPipeline().getId());
            StageResponse sr = new StageResponse();
            sr.setId(stage.getId());
            sr.setName(stage.getName());
            if (idsList == null) {
                idsList = new ArrayList<>();
                idsList.add(sr);
                result.put(stage.getPipeline().getId(), idsList);
            } else {
                idsList.add(sr);
            }
        }

        return result;
    }

    @Override
    public Stage store(StageStoreRequest stageStoreRequest) {
        Pipeline pipeline = this.pipelinesRepository.findById(stageStoreRequest.getPipelineId()).orElseThrow(() -> new ResourceNotFoundException("No pipeline found"));
        Integer currentMaxOrder = stagesRepository.getMaxOrderInPipeline(pipeline);
        return this.stagesRepository.save(
                new Stage(pipeline, stageStoreRequest.getName(), currentMaxOrder + 1)
        );
    }

    @Override
    public Stage update(Long id, StageUpdateRequest stageUpdateRequest) {
        Stage stage = this.stagesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No stage found"));
        stage.setName(stageUpdateRequest.getName());
        return this.stagesRepository.save(stage);
    }

    @Override
    @Transactional
    public Stage updateOrder(Long id, StageOrderUpdateRequest stageOrderUpdateRequest) throws InvalidOrderException {
        Stage stage = this.stagesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No stage found"));
        Integer oldOrder = stage.getStageOrder();
        Integer newOrder = stageOrderUpdateRequest.getOrder();

        // nothing to update
        if (Objects.equals(oldOrder, newOrder)) {
            return stage;
        }

        // validate if new order is inside the limits
        Integer currentMaxOrder = this.stagesRepository.getMaxOrderInPipeline(stage.getPipeline());
        if (newOrder > currentMaxOrder) {
            throw new InvalidOrderException("New order cannot be greater than the maximum order");
        }

        // take care of other stages
        this.stagesRepository.decrementAfterOrder(oldOrder, stage.getPipeline());
        this.stagesRepository.incrementAfterOrder(newOrder, stage.getPipeline());

        // update this stage
        stage.setStageOrder(newOrder);
        return this.stagesRepository.save(stage);
    }

    @Override
    public void delete(Long id) {
        this.stagesRepository.deleteById(id);
    }
}
