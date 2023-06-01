package com.example.lr20190024.stages.seeders;

import com.example.lr20190024.clients.entities.Client;
import com.example.lr20190024.clients.repositories.ClientsRepository;
import com.example.lr20190024.deals.entities.Deal;
import com.example.lr20190024.deals.enums.DealStatus;
import com.example.lr20190024.deals.repositories.DealsRepository;
import com.example.lr20190024.pipelines.entities.Pipeline;
import com.example.lr20190024.pipelines.repositories.PipelinesRepository;
import com.example.lr20190024.stages.entities.Stage;
import com.example.lr20190024.stages.repositories.StagesRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class StagesSeeder {
    private PipelinesRepository pipelinesRepository;
    private StagesRepository stagesRepository;
    private ClientsRepository clientsRepository;
    private DealsRepository dealsRepository;

    @PostConstruct
    public void seed() {
        List<Client> clients = clientsRepository.findAll();

        for (Pipeline pipeline : pipelinesRepository.findAll()) {
            for (int j = 1; j <= 3; j++) {
                Stage stage = stagesRepository.save(new Stage(pipeline, "Stage " + j, j));

                for (Client client : clients) {
                    this.dealsRepository.save(
                            new Deal(
                                    "Deal " + stage.getId(),
                                    DealStatus.values()[new Random().nextInt(DealStatus.values().length)],
                                    client,
                                    stage
                            )
                    );
                }
            }
        }
    }
}
