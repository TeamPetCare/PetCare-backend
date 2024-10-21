package com.application.petcare.repository;

import com.application.petcare.entities.PlanType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanTypeRepository extends JpaRepository<PlanType, Integer> {
}
