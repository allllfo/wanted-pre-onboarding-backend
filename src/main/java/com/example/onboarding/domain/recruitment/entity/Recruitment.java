package com.example.onboarding.domain.recruitment.entity;

import com.example.onboarding.domain.company.entity.Company;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String name;
    private String position;
    private Integer reward;
    private String stack;

    @Builder
    public Recruitment(String name, String position, Integer reward, String stack) {
        this.name = name;
        this.position = position;
        this.reward = reward;
        this.stack = stack;
    }
}
