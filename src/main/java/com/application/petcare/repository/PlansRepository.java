package com.application.petcare.repository;

import com.application.petcare.entities.Plans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlansRepository extends JpaRepository<Plans, Integer> {
    List<Plans> findAllByDeletedAtIsNull();
}
