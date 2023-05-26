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
    private String notes;
    private DealStatus dealStatus;
    private Instant createdAt;
    private Instant updatedAt;

    public static DealResponse fromEntity(Deal deal) {
        return new DealResponse(
                deal.getId(),
                deal.getName(),
                deal.getNotes(),
                deal.getDealStatus(),
                deal.getCreatedAt(),
                deal.getUpdatedAt()
        );
    }
}
