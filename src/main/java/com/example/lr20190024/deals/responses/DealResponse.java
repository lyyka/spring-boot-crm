package com.example.lr20190024.deals.responses;

import com.example.lr20190024.deals.entities.Deal;
import com.example.lr20190024.deals.enums.DealStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class DealResponse {
    private Long id;
    private String name;
    private DealStatus dealStatus;
    private Instant createdAt;
    private Instant updatedAt;
    private Long stageId;
    private String stageName;
    private Long pipelineId;
    private String pipelineName;

    public static DealResponse fromEntity(Deal deal) {
        return new DealResponse(
                deal.getId(),
                deal.getName(),
                deal.getDealStatus(),
                deal.getCreatedAt(),
                deal.getUpdatedAt(),
                deal.getStage().getId(),
                deal.getStage().getName(),
                deal.getStage().getPipeline().getId(),
                deal.getStage().getPipeline().getName()
        );
    }
}
