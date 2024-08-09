package com.example.onboarding.domain.company.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String country;
    private String location;

    @Builder
    public Company(String name, String country, String location) {
        this.name = name;
        this.country = country;
        this.location = location;
    }
}
