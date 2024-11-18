package com.application.petcare.repository;

import com.application.petcare.entities.Specie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SpecieRepository extends JpaRepository<Specie, Integer> {
    List<Specie> findAllByDeletedAtIsNull();

}
