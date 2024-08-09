package com.example.onboarding.domain.recruitment.controller.dto;

import com.example.onboarding.domain.company.entity.Company;
import com.example.onboarding.domain.recruitment.entity.Recruitment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RecruitmentFindAllResponse {
    private Integer id;
    private String companyName;
    private String country;
    private String location;
    private String position;
    private Integer reward;
    private String stack;

    public static RecruitmentFindAllResponse from(Recruitment recruitment){
        return RecruitmentFindAllResponse.builder()
                .id(recruitment.getId())
                .companyName(recruitment.getCompany().getName())
                .country(recruitment.getCompany().getCountry())
                .location(recruitment.getCompany().getLocation())
                .position(recruitment.getPosition())
                .reward(recruitment.getReward())
                .stack(recruitment.getStack())
                .build();
    }
}
