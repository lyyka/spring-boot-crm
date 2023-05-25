package com.example.lr20190024.pipelines.responses;

import com.example.lr20190024.pipelines.entities.Pipeline;
import com.example.lr20190024.stages.responses.StageResponse;
import lombok.Data;

import java.util.List;

@Data
public class PipelineResponse {
    private Long id;
    private String name;
    private List<StageResponse> stages;

    public static PipelineResponse fromEntity(Pipeline pipeline) {
        PipelineResponse response = new PipelineResponse();
        response.setId(pipeline.getId());
        response.setName(pipeline.getName());
        return response;
    }
}
