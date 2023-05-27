package com.example.lr20190024.pipelines.services.impl;

import com.example.lr20190024.common.exception.ResourceNotFoundException;
import com.example.lr20190024.pipelines.entities.Pipeline;
import com.example.lr20190024.pipelines.repositories.PipelinesRepository;
import com.example.lr20190024.pipelines.requests.PipelineStoreRequest;
import com.example.lr20190024.pipelines.responses.PipelineResponse;
import com.example.lr20190024.pipelines.services.IPipelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PipelineService implements IPipelineService {
    private final PipelinesRepository pipelinesRepository;

    @Override
    public Page<PipelineResponse> paginate(Pageable pageable) {
        Page<Pipeline> page = this.pipelinesRepository.findAll(pageable);
        return new PageImpl<>(
                page.getContent().stream().map(PipelineResponse::fromEntity).toList(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public List<PipelineResponse> getAll() {
        return this.pipelinesRepository.findAll().stream().map(PipelineResponse::fromEntity).toList();
    }

    @Override
    public PipelineResponse get(Long id) {
        return PipelineResponse.fromEntity(
                this.pipelinesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pipeline not found"))
        );
    }

    @Override
    public Pipeline store(PipelineStoreRequest pipelineStoreRequest) {
        return this.pipelinesRepository.save(pipelineStoreRequest.toEntity());
    }

    @Override
    public Pipeline update(Long id, PipelineStoreRequest pipelineStoreRequest) {
        Pipeline toUpdate = this.pipelinesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No pipeline found"));
        toUpdate.setName(pipelineStoreRequest.getName());
        return this.pipelinesRepository.save(toUpdate);
    }

    @Override
    public void delete(Long id) {
        this.pipelinesRepository.deleteById(id);
    }
}
