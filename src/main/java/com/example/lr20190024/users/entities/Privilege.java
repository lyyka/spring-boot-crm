package com.example.lr20190024.users.entities;

import com.example.lr20190024.common.jpa.BaseEntity;
import com.example.lr20190024.users.enums.PrivilegeName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Privilege extends BaseEntity {
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private PrivilegeName name;
}
