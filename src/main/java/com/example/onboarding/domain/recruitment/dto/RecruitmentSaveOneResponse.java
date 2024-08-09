package com.example.onboarding.domain.recruitment.dto;

import com.example.onboarding.domain.recruitment.entity.Recruitment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RecruitmentSaveOneResponse {

    public Integer id;

    public static RecruitmentSaveOneResponse from(Recruitment recruitment){
        return RecruitmentSaveOneResponse.builder()
                .id(recruitment.getId())
                .build();
    }
}
