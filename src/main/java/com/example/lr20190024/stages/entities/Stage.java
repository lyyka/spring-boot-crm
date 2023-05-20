package com.example.lr20190024.stages.entities;

import com.example.lr20190024.common.jpa.BaseEntity;
import com.example.lr20190024.pipelines.entities.Pipeline;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stage extends BaseEntity {
    @JsonIgnore
    @ManyToOne(targetEntity = Pipeline.class, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Pipeline pipeline;
    private String name;
}
