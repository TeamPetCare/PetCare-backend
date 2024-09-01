package com.application.petcare.repository;

import com.application.petcare.entities.Credito;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditoRepository extends JpaRepository<Credito, Integer> {
}
