package com.example.lr20190024.pipelines.entities;

import com.example.lr20190024.common.jpa.BaseEntity;
import com.example.lr20190024.stages.entities.Stage;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pipeline extends BaseEntity {
    private String name;
    @OneToMany(targetEntity = Stage.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "pipeline_id", referencedColumnName = "id")
    @ToString.Exclude
    private Set<Stage> stages = new HashSet<>();
}
