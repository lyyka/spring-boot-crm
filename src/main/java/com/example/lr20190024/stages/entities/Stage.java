package com.example.lr20190024.stages.entities;

import com.example.lr20190024.common.jpa.BaseEntity;
import com.example.lr20190024.pipelines.entities.Pipeline;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stage extends BaseEntity {
    @ManyToOne(targetEntity = Pipeline.class, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "pipeline_id")
    private Pipeline pipeline;
    private String name;
    @Positive
    private Integer stageOrder;
}
