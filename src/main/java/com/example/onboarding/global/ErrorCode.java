package com.example.onboarding.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //회사 예외
    NOT_FOUND_COMPANY(HttpStatus.NOT_FOUND, "해당 회사를 찾을 수 없습니다."),

    //채용공고 예외
    NOT_FOUND_RECRUITMENT(HttpStatus.NOT_FOUND, "해당 채용공고를 찾을 수 없습니다.");


    private final HttpStatus status;
    private final String message;
}
