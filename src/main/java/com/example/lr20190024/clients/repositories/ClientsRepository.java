package com.example.lr20190024.clients.repositories;

import com.example.lr20190024.clients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Client, Long> {

}
