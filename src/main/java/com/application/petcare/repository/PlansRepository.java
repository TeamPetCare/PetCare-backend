package com.application.petcare.repository;

import com.application.petcare.entities.Plans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlansRepository extends JpaRepository<Plans, Integer> {
}
