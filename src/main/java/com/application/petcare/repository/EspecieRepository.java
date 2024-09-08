package com.application.petcare.repository;

import com.application.petcare.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, UUID> {
}
