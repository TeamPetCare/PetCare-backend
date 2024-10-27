package com.application.petcare.repository;

import com.application.petcare.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicesRepository extends JpaRepository<Services, Integer> {
    Optional<List<Services>> findAllByIdIn (List<Integer> ids);
}
