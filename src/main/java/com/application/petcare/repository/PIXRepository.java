package com.application.petcare.repository;

import com.application.petcare.entities.PIX;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PIXRepository extends JpaRepository<PIX, Integer> {
}
