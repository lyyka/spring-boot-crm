package com.example.lr20190024.pipelines.services;

import com.example.lr20190024.pipelines.entities.Pipeline;
import com.example.lr20190024.pipelines.requests.PipelineStoreRequest;

import java.util.List;

public interface IPipelineService {
    List<Pipeline> getAll();

    Pipeline store(PipelineStoreRequest pipelineStoreRequest);

    Pipeline update(Long id, PipelineStoreRequest pipelineStoreRequest);

    void delete(Long id);
}
