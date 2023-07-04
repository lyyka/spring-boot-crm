package com.example.lr20190024.pipelines.seeders;

import com.example.lr20190024.pipelines.entities.Pipeline;
import com.example.lr20190024.pipelines.repositories.PipelinesRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class PipelinesSeeder {
    private PipelinesRepository pipelinesRepository;

    @PostConstruct
    public void seed() {
        pipelinesRepository.save(new Pipeline("Stambeni kredit", new HashSet<>()));
        pipelinesRepository.save(new Pipeline("Kes kredit", new HashSet<>()));
        pipelinesRepository.save(new Pipeline("Kupovina nekretnine", new HashSet<>()));
    }
}
