package com.example.lr20190024.stages.services;

import com.example.lr20190024.stages.entities.Stage;
import com.example.lr20190024.stages.exceptions.InvalidOrderException;
import com.example.lr20190024.stages.requests.StageOrderUpdateRequest;
import com.example.lr20190024.stages.requests.StageStoreRequest;
import com.example.lr20190024.stages.requests.StageUpdateRequest;
import com.example.lr20190024.stages.responses.StageResponse;

import java.util.List;
import java.util.Map;

public interface IStageService {
    List<StageResponse> getForPipeline(Long id);

    StageResponse get(Long id);

    Map<Long, List<StageResponse>> getStagesPerPipeline();

    Stage store(StageStoreRequest stageStoreRequest);

    Stage update(Long id, StageUpdateRequest stageUpdateRequest);

    Stage updateOrder(Long id, StageOrderUpdateRequest stageOrderUpdateRequest) throws InvalidOrderException;

    void delete(Long id);
}
