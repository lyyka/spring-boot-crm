package com.example.lr20190024.pipelines.requests;

import com.example.lr20190024.pipelines.entities.Pipeline;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PipelineStoreRequest {
    private String name;

    public Pipeline toEntity() {
        return new Pipeline(name, new HashSet<>());
    }
}
