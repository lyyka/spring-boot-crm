package com.example.lr20190024.stages.requests;


import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StageStoreRequest {
    private String name;
    @Positive
    private Long pipelineId;
}
