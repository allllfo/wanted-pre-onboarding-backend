package com.example.onboarding.domain.recruitment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RecruitmentSaveOneRequest {

    private Integer companyId;
    private String position;
    private Integer reward;
    private String description;
    private String stack;
}
