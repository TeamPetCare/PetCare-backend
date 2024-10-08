package com.application.petcare.repository;

import com.application.petcare.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EspecieRepository extends JpaRepository<Especie, Integer> {
}
