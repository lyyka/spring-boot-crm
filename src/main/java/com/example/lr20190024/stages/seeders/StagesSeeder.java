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
import java.util.Objects;
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

        Pipeline stambeniKredit = null;
        Pipeline kesKredit = null;
        Pipeline nekretnine = null;

        for (Pipeline pipeline : pipelinesRepository.findAll()) {
            if (Objects.equals(pipeline.getName(), "Stambeni kredit")) stambeniKredit = pipeline;
            else if (Objects.equals(pipeline.getName(), "Kes kredit")) kesKredit = pipeline;
            else if (Objects.equals(pipeline.getName(), "Kupovina nekretnine")) nekretnine = pipeline;
        }

        Stage stambeniStage1 = stagesRepository.save(new Stage(stambeniKredit, "Poziv", 1));
        Stage stambeniStage2 = stagesRepository.save(new Stage(stambeniKredit, "Prikupljanje dokumentacije", 2));
        Stage stambeniStage3 = stagesRepository.save(new Stage(stambeniKredit, "Provera kreditnog biroa", 3));
        Stage stambeniStage4 = stagesRepository.save(new Stage(stambeniKredit, "Sastanak sa bankom", 4));
        Stage stambeniStage5 = stagesRepository.save(new Stage(stambeniKredit, "Isplata kredita", 5));

        Stage kesStage1 = stagesRepository.save(new Stage(kesKredit, "Poziv", 1));
        Stage kesStage2 = stagesRepository.save(new Stage(kesKredit, "Prikupljanje dokumentacije", 2));
        Stage kesStage3 = stagesRepository.save(new Stage(kesKredit, "Sastanak sa bankom", 3));
        Stage kesStage4 = stagesRepository.save(new Stage(kesKredit, "Isplata kredita", 4));

        Stage nekretnineStage1 = stagesRepository.save(new Stage(nekretnine, "Poziv", 1));
        Stage nekretnineStage2 = stagesRepository.save(new Stage(nekretnine, "Trazenje nekretnine", 2));
        Stage nekretnineStage3 = stagesRepository.save(new Stage(nekretnine, "Zakazano razgledanje", 3));
        Stage nekretnineStage4 = stagesRepository.save(new Stage(nekretnine, "Rezervacija", 4));
        Stage nekretnineStage5 = stagesRepository.save(new Stage(nekretnine, "Placanje obavljeno", 5));

        Stage[] stages = {
                stambeniStage1,
                stambeniStage2,
                stambeniStage3,
                stambeniStage4,
                stambeniStage5,

                kesStage1,
                kesStage2,
                kesStage3,
                kesStage4,

                nekretnineStage1,
                nekretnineStage2,
                nekretnineStage3,
                nekretnineStage4,
                nekretnineStage5,
        };

        for (int i = 0; i < 100; i++) {
            Client randomClient = clients.get(new Random().nextInt(clients.size()));
            this.dealsRepository.save(
                    new Deal(
                            randomClient.getFirstName() + " " + randomClient.getLastName(),
                            DealStatus.values()[new Random().nextInt(DealStatus.values().length)],
                            randomClient,
                            stages[new Random().nextInt(stages.length)]
                    )
            );
        }
    }
}
