package com.example.onboarding.domain.recruitment.repository;

import com.example.onboarding.domain.company.entity.Company;
import com.example.onboarding.domain.recruitment.controller.dto.RecruitmentSearchAllResponse;
import com.example.onboarding.domain.recruitment.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {

    @Query("SELECT r FROM Recruitment r " +
            "JOIN r.company c " +
            "WHERE c.name LIKE %:searchTag% " +
            "OR c.country LIKE %:searchTag% " +
            "OR c.location LIKE %:searchTag% " +
            "OR r.position LIKE %:searchTag% " +
            "OR r.stack LIKE %:searchTag%")
    List<Recruitment> findBySearchTag(@Param("searchTag") String searchTag);

    @Query("SELECT r.id FROM Recruitment r WHERE r.company.id = :companyId")
    List<Integer> findByCompanyId(@Param("companyId") Integer companyId);
}
