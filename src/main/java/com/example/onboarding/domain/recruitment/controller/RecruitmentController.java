package com.example.onboarding.domain.recruitment.controller;

import com.example.onboarding.domain.recruitment.dto.RecruitmentSaveOneRequest;
import com.example.onboarding.domain.recruitment.dto.RecruitmentSaveOneResponse;
import com.example.onboarding.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruitments")
@Slf4j
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    //채용공고 등록
    @PostMapping("")
    public ResponseEntity<RecruitmentSaveOneResponse> saveRecruitment(@RequestBody RecruitmentSaveOneRequest request){
        RecruitmentSaveOneResponse response = recruitmentService.saveRecruitment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //채용공고 수정
    @PostMapping()

    //채용공고 삭제

    //채용공고 목록 조회

    //채용공고 검색

    //채용상세페이지 조회
}
