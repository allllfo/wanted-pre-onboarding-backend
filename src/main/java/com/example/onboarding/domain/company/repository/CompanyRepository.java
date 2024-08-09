package com.example.onboarding.domain.company.repository;

import com.example.onboarding.domain.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
