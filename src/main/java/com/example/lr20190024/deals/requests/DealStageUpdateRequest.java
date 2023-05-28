package com.example.lr20190024.deals.requests;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DealStageUpdateRequest {
    @Positive
    private Long stageId;
}
