package com.example.onboarding.domain.recruitment.repository;

import com.example.onboarding.domain.recruitment.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {
}
