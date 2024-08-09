package com.example.onboarding.domain.recruitment.service;

import com.example.onboarding.domain.company.entity.Company;
import com.example.onboarding.domain.company.repository.CompanyRepository;
import com.example.onboarding.domain.recruitment.dto.RecruitmentSaveOneRequest;
import com.example.onboarding.domain.recruitment.dto.RecruitmentSaveOneResponse;
import com.example.onboarding.domain.recruitment.entity.Recruitment;
import com.example.onboarding.domain.recruitment.repository.RecruitmentRepository;
import com.example.onboarding.global.CustomException;
import com.example.onboarding.global.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;

    public RecruitmentSaveOneResponse saveRecruitment(RecruitmentSaveOneRequest request) {
        System.out.println(request.getCompanyId());
        System.out.println("djfal;sdfjasl;dfjlk;asdjf");
        Company findCompany = findCompanyById(request.getCompanyId());

        Recruitment recruitment = Recruitment.builder()
                .company(findCompany)
                .description(request.getDescription())
                .position(request.getPosition())
                .reward(request.getReward())
                .stack(request.getStack())
                .build();

        recruitmentRepository.save(recruitment);
        return RecruitmentSaveOneResponse.from(recruitment);
    }

    private Company findCompanyById(Integer companyId){
        return companyRepository.findById(companyId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_COMPANY));
    }
}
