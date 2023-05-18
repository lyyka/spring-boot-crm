package com.example.lr20190024.stages.seeders;

import com.example.lr20190024.pipelines.entities.Pipeline;
import com.example.lr20190024.pipelines.repositories.PipelinesRepository;
import com.example.lr20190024.stages.entities.Stage;
import com.example.lr20190024.stages.repositories.StagesRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StagesSeeder {
    private PipelinesRepository pipelinesRepository;
    private StagesRepository stagesRepository;

    @PostConstruct
    public void seed() {
        for (Pipeline pipeline : pipelinesRepository.findAll()) {
            for (int j = 1; j <= 3; j++) {
                stagesRepository.save(new Stage(pipeline, "Stage " + j));
            }
        }
    }
}
