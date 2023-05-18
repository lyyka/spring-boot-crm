package com.example.lr20190024.clients.seeders;

import com.example.lr20190024.clients.entities.Client;
import com.example.lr20190024.clients.repositories.ClientsRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientsSeeder {
    private ClientsRepository clientsRepository;

    @PostConstruct
    public void seed() {
        clientsRepository.save(new Client("Angela", "Fiorentino", "claude2017@hotmail.com", "215-920-3778"));
        clientsRepository.save(new Client("Michele", "Thomas", "gabrielle1982@yahoo.com", "605-351-7662"));
        clientsRepository.save(new Client("Elizabeth", "Chittenden", "kelly_gutkows@hotmail.com", "928-727-5163"));
        clientsRepository.save(new Client("Nicholas", "Stevenson", "carlie1971@hotmail.com", "970-326-8139"));
        clientsRepository.save(new Client("Marlo", "Cornett", "kelton.thomps@gmail.com", "701-389-7776"));
    }
}
