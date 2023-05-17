package com.example.lr20190024.clients.entities;

import com.example.lr20190024.common.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client extends BaseEntity {
    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String phoneNumber;
}
