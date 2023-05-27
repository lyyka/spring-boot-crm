package com.example.lr20190024.clients.repositories;

import com.example.lr20190024.clients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClientsRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

}
