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

    //채용공고 등록
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

    //채용공고 수정
    public void updateRecruitment(RecruitmentUpdateOneRequest request, Integer recruitmentId) {
        Recruitment findRecruitment = findRecruitmentById(recruitmentId);
        findRecruitment.update(request.getPosition(), request.getReward(), request.getDescription(), request.getStack());
    }

    //전체 채용공고 조회
    public List<RecruitmentFindAllResponse> findAllRecruitments() {
        List<RecruitmentFindAllResponse> response = recruitmentRepository.findAll().stream()
                .map(recruitment -> {
                    return RecruitmentFindAllResponse.from(recruitment);
                }).toList();
        return response;
    }

    //채용공고 검색
    public List<RecruitmentSearchAllResponse> searchAllRecruitments(String searchTag) {
        List<RecruitmentSearchAllResponse> response = recruitmentRepository.findBySearchTag(searchTag)
                .stream()
                .map(recruitment -> {
                    return RecruitmentSearchAllResponse.from(recruitment);
                }).toList();
        return response;
    }

    //상세페이지 조회
    public RecruitmentDetailFindOneResponse findRecruitmentDetail(Integer recruitmentId) {
        Recruitment findRecruitment = findRecruitmentById(recruitmentId);
        List<Integer> anotherRecruitments = recruitmentRepository.findByCompanyId(findRecruitment.getCompany().getId());

        return RecruitmentDetailFindOneResponse.of(findRecruitment, anotherRecruitments);
    }

    //채용공고 삭제
    public void deleteRecruitment(Integer recruitmentId) {
        Recruitment findRecruitment = findRecruitmentById(recruitmentId);
        recruitmentRepository.delete(findRecruitment);
    }

    //comapnyId값으로 company찾기
    private Company findCompanyById(Integer companyId){
        return companyRepository.findById(companyId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_COMPANY));
    }

    //recruitmentId값으로 recruitment찾기
    private Recruitment findRecruitmentById(Integer recruitmentId){
        return recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_RECRUITMENT));
    }


}
