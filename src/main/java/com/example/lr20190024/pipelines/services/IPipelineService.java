package com.example.lr20190024.pipelines.services;

import com.example.lr20190024.pipelines.entities.Pipeline;
import com.example.lr20190024.pipelines.requests.PipelineStoreRequest;
import com.example.lr20190024.pipelines.responses.PipelineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPipelineService {
    Page<PipelineResponse> paginate(Pageable pageable);

    List<PipelineResponse> getAll();

    PipelineResponse get(Long id);

    Pipeline store(PipelineStoreRequest pipelineStoreRequest);

    Pipeline update(Long id, PipelineStoreRequest pipelineStoreRequest);

    void delete(Long id);
}
