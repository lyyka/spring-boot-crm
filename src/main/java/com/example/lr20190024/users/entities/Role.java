package com.example.lr20190024.users.entities;

import com.example.lr20190024.common.jpa.BaseEntity;
import com.example.lr20190024.users.enums.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role extends BaseEntity {
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Privilege> privileges = new HashSet<>();
}
