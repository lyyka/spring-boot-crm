package com.example.lr20190024.stages.services;

import com.example.lr20190024.stages.entities.Stage;
import com.example.lr20190024.stages.requests.StageStoreRequest;

import java.util.List;

public interface IStageService {
    List<Stage> getAll();

    Stage store(StageStoreRequest stageStoreRequest);

    Stage update(Long id, StageStoreRequest stageStoreRequest);

    void delete(Long id);
}
