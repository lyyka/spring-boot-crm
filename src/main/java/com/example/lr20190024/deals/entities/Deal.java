package com.example.lr20190024.deals.entities;

import com.example.lr20190024.clients.entities.Client;
import com.example.lr20190024.common.jpa.BaseEntity;
import com.example.lr20190024.deals.enums.DealStatus;
import com.example.lr20190024.stages.entities.Stage;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Deal extends BaseEntity {
    private String name;

    @Enumerated(EnumType.STRING)
    private DealStatus dealStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Client client;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Stage stage;
}
