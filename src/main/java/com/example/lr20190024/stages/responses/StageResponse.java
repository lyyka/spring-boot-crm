package com.example.lr20190024.stages.responses;

import com.example.lr20190024.pipelines.responses.PipelineResponse;
import com.example.lr20190024.stages.entities.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StageResponse {
    private Long id;
    private String name;
    private PipelineResponse pipeline;

    public static StageResponse fromEntity(Stage stage) {
        return new StageResponse(
                stage.getId(),
                stage.getName(),
                PipelineResponse.fromEntity(stage.getPipeline())
        );
    }
}
