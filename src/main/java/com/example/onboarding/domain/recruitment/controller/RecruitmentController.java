package com.example.onboarding.domain.recruitment.controller;

import com.example.onboarding.domain.recruitment.controller.dto.*;
import com.example.onboarding.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/{recruitmentId}")
    public ResponseEntity<Void> updateRecruitment(@RequestBody RecruitmentUpdateOneRequest request, @PathVariable Integer recruitmentId){
        recruitmentService.updateRecruitment(request, recruitmentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //채용공고 삭제
    @DeleteMapping("/{recruitmentId}")
    public ResponseEntity<Void> deleteRecruitment(@PathVariable Integer recruitmentId){
        recruitmentService.deleteRecruitment(recruitmentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //채용공고 목록 조회
    @GetMapping("all")
    public ResponseEntity<List<RecruitmentFindAllResponse>> findAllRecruitments(){
        List<RecruitmentFindAllResponse> response = recruitmentService.findAllRecruitments();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    //채용공고 검색
    @GetMapping("")
    public ResponseEntity<List<RecruitmentSearchAllResponse>> searchRecruitments(@RequestParam(name = "search") String searchTag){
        List<RecruitmentSearchAllResponse> response = recruitmentService.searchAllRecruitments(searchTag);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //채용상세페이지 조회
    @GetMapping("/{recruitmentId}")
    public ResponseEntity<RecruitmentDetailFindOneResponse> findRecruitmentDetail(@PathVariable Integer recruitmentId){
        RecruitmentDetailFindOneResponse response = recruitmentService.findRecruitmentDetail(recruitmentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
