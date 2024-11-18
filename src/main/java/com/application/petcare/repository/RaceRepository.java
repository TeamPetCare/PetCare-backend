package com.application.petcare.repository;

import com.application.petcare.entities.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RaceRepository extends JpaRepository<Race, Integer> {
    List<Race> findAllByDeletedAtIsNull();
}