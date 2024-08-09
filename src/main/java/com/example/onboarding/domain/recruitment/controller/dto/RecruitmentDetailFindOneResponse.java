package com.example.onboarding.domain.recruitment.controller.dto;

import com.example.onboarding.domain.recruitment.entity.Recruitment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RecruitmentDetailFindOneResponse {
    private Integer id;
    private String companyName;
    private String country;
    private String location;
    private String position;
    private Integer reward;
    private String stack;
    private String description;
    private List<Integer> anotherRecruitments;

    public static RecruitmentDetailFindOneResponse of(Recruitment recruitment, List<Integer> anotherRecruitments){
        return RecruitmentDetailFindOneResponse.builder()
                .id(recruitment.getId())
                .companyName(recruitment.getCompany().getName())
                .country(recruitment.getCompany().getCountry())
                .location(recruitment.getCompany().getLocation())
                .position(recruitment.getPosition())
                .reward(recruitment.getReward())
                .stack(recruitment.getStack())
                .description(recruitment.getDescription())
                .anotherRecruitments(anotherRecruitments)
                .build();
    }
}
