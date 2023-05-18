package com.example.lr20190024.stages.services.impl;

import com.example.lr20190024.common.exception.ResourceNotFoundException;
import com.example.lr20190024.pipelines.entities.Pipeline;
import com.example.lr20190024.pipelines.repositories.PipelinesRepository;
import com.example.lr20190024.stages.entities.Stage;
import com.example.lr20190024.stages.repositories.StagesRepository;
import com.example.lr20190024.stages.requests.StageStoreRequest;
import com.example.lr20190024.stages.services.IStageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StageService implements IStageService {
    private final StagesRepository stagesRepository;
    private final PipelinesRepository pipelinesRepository;

    @Override
    public List<Stage> getAll() {
        return this.stagesRepository.findAll();
    }

    @Override
    public Stage store(StageStoreRequest stageStoreRequest) {
        Pipeline pipeline = this.pipelinesRepository.findById(stageStoreRequest.getPipelineId()).orElseThrow(() -> new ResourceNotFoundException("No pipeline found"));
        return this.stagesRepository.save(
                new Stage(pipeline, stageStoreRequest.getName())
        );
    }

    @Override
    public Stage update(Long id, StageStoreRequest stageStoreRequest) {
        Pipeline pipeline = this.pipelinesRepository.findById(stageStoreRequest.getPipelineId()).orElseThrow(() -> new ResourceNotFoundException("No pipeline found"));
        Stage stage = this.stagesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No stage found"));
        stage.setName(stageStoreRequest.getName());
        stage.setPipeline(pipeline);
        return this.stagesRepository.save(stage);
    }

    @Override
    public void delete(Long id) {
        this.stagesRepository.deleteById(id);
    }
}
