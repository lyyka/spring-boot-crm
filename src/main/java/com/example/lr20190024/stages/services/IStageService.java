package com.example.lr20190024.stages.services;

import com.example.lr20190024.stages.entities.Stage;
import com.example.lr20190024.stages.requests.StageStoreRequest;
import com.example.lr20190024.stages.requests.StageUpdateRequest;
import com.example.lr20190024.stages.responses.StageResponse;

import java.util.List;

public interface IStageService {
    List<StageResponse> getForPipeline(Long id);

    StageResponse get(Long id);

    Stage store(StageStoreRequest stageStoreRequest);

    Stage update(Long id, StageUpdateRequest stageUpdateRequest);

    void delete(Long id);
}
