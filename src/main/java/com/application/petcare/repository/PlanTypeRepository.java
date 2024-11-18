package com.application.petcare.repository;

import com.application.petcare.entities.PlanType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanTypeRepository extends JpaRepository<PlanType, Integer> {
    List<PlanType> findAllByDeletedAtIsNull();
}
