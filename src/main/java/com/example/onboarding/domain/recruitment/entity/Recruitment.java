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

    private String position;
    private Integer reward;
    private String description;
    private String stack;

    @Builder
    public Recruitment(Company company, String position, Integer reward, String description, String stack) {
        this.company = company;
        this.position = position;
        this.reward = reward;
        this.description = description;
        this.stack = stack;
    }
}
