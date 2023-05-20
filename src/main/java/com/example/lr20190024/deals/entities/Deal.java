package com.example.lr20190024.deals.entities;

import com.example.lr20190024.clients.entities.Client;
import com.example.lr20190024.common.jpa.BaseEntity;
import com.example.lr20190024.deals.enums.DealStatus;
import com.example.lr20190024.stages.entities.Stage;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Deal extends BaseEntity {
    private String name;

    private String notes;

    @Enumerated(EnumType.STRING)
    private DealStatus dealStatus;

    @OneToOne
    private Client client;

    @OneToOne
    private Stage stage;
}
