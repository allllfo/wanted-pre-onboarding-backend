package com.example.onboarding.domain.recruitment.service;

import com.example.onboarding.domain.company.entity.Company;
import com.example.onboarding.domain.company.repository.CompanyRepository;
import com.example.onboarding.domain.recruitment.controller.dto.*;
import com.example.onboarding.domain.recruitment.entity.Recruitment;
import com.example.onboarding.domain.recruitment.repository.RecruitmentRepository;
import com.example.onboarding.global.CustomException;
import com.example.onboarding.global.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;

    public RecruitmentSaveOneResponse saveRecruitment(RecruitmentSaveOneRequest request) {
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

    public void updateRecruitment(RecruitmentUpdateOneRequest request, Integer recruitmentId) {
        Recruitment recruitment = findRecruitmentById(recruitmentId);
        recruitment.update(request.getPosition(), request.getReward(), request.getDescription(), request.getStack());
    }



    private Company findCompanyById(Integer companyId){
        return companyRepository.findById(companyId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_COMPANY));
    }

    private Recruitment findRecruitmentById(Integer recruitmentId){
        return recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_RECRUITMENT));
    }

    public List<RecruitmentFindAllResponse> findAllRecruitments() {
        List<RecruitmentFindAllResponse> response = recruitmentRepository.findAll().stream()
                .map(recruitment -> {
                    return RecruitmentFindAllResponse.from(recruitment);
                }).toList();
        return response;
    }

    public List<RecruitmentSearchAllResponse> searchAllRecruitments(String searchTag) {
        List<RecruitmentSearchAllResponse> response = recruitmentRepository.findBySearchTag(searchTag)
                .stream()
                .map(recruitment -> {
                    return RecruitmentSearchAllResponse.from(recruitment);
                }).toList();
        return response;
    }
}
