package com.example.onboarding.domain.recruitment.service;

import com.example.onboarding.domain.company.entity.Company;
import com.example.onboarding.domain.recruitment.controller.dto.*;
import com.example.onboarding.domain.recruitment.entity.Recruitment;
import com.example.onboarding.domain.recruitment.repository.RecruitmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
@Transactional
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecruitmentServiceTest {

    @Autowired EntityManager em;
    @Autowired RecruitmentService recruitmentService;
    @Autowired RecruitmentRepository recruitmentRepository;

    @Test
    public void 채용공고등록_상세페이지조회() throws Exception{
        //given
        Company company = createCompany("테스트회사");

        RecruitmentSaveOneRequest request = new RecruitmentSaveOneRequest().builder()
                .companyId(company.getId())
                .position("백엔드")
                .stack("파이썬")
                .reward(10000)
                .description("테스트1에대한 설명")
                .build();

        //when
        RecruitmentSaveOneResponse saveResponse = recruitmentService.saveRecruitment(request);
        RecruitmentDetailFindOneResponse detailResponse = recruitmentService.findRecruitmentDetail(saveResponse.getId());
        //then
        assertThat(request.getDescription()).isEqualTo(detailResponse.getDescription());
    }


    @Test
    public void 채용공고수정() throws Exception{
        //given
        Company company = createCompany("테스트회사");
        Recruitment recruitment = createRecruitment(company, "1번째 채용공고");
        RecruitmentUpdateOneRequest request = new RecruitmentUpdateOneRequest().builder()
                .position(recruitment.getPosition())
                .reward(recruitment.getReward())
                .stack(recruitment.getStack())
                .description("바뀐 1번째 채용공고")
                .build();

        //when
        recruitmentService.updateRecruitment(request, recruitment.getId());
        RecruitmentDetailFindOneResponse detailResponse = recruitmentService.findRecruitmentDetail(recruitment.getId());

        //then
        assertThat("바뀐 1번째 채용공고").isEqualTo(detailResponse.getDescription());

    }

    @Test
    public void 채용공고조회() throws Exception{
        //given
        Company firstCompany = createCompany("첫번째회사");
        Recruitment firstRecruitment = createRecruitment(firstCompany, "1번째 채용공고");
        Company secondCompany = createCompany("두번째회사");
        Recruitment secondRecruitment = createRecruitment(secondCompany, "2번째 채용공고");

        //when
        List<RecruitmentFindAllResponse> response = recruitmentService.findAllRecruitments();

        //then
        assertThat(response.get(0).getCompanyName()).isEqualTo("첫번째회사");
        assertThat(response.get(1).getCompanyName()).isEqualTo("두번째회사");
    }

    @Test
    public void 채용공고검색() throws Exception{
        //given
        Company firstCompany = createCompany("첫번째회사");
        Recruitment firstRecruitment = createRecruitment(firstCompany, "1번째 채용공고");
        Company secondCompany = createCompany("두번째회사");
        Recruitment secondRecruitment = createRecruitment(secondCompany, "2번째 채용공고");

        //when
        List<RecruitmentSearchAllResponse> firstResponse = recruitmentService.searchAllRecruitments("첫번째");
        List<RecruitmentSearchAllResponse> allResponse = recruitmentService.searchAllRecruitments("째");

        //then
        assertThat(firstResponse.size()).isEqualTo(1);
        assertThat(allResponse.size()).isEqualTo(2);
    }

    @Test
    public void 채용공고삭제() throws Exception{
        //given
        Company company = createCompany("테스트회사");
        Recruitment recruitment = createRecruitment(company, "1번째 채용공고");

        //when
        recruitmentService.deleteRecruitment(recruitment.getId());
        List<RecruitmentFindAllResponse> response = recruitmentService.findAllRecruitments();

        //then
        assertThat(response.size()).isEqualTo(0);
    }

    private Company createCompany(String name) {
        Company company = Company.builder()
                .name(name)
                .country("대한민국")
                .location("서울")
                .build();
        em.persist(company);
        return company;
    }

    private Recruitment createRecruitment(Company company, String description){
        Recruitment recruitment = Recruitment.builder()
                .company(company)
                .position("프론트")
                .stack("리액트")
                .reward(1000)
                .description(description)
                .build();
        em.persist(recruitment);
        return recruitment;
    }
}