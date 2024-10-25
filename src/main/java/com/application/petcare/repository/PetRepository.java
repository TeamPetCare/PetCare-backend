package com.application.petcare.repository;

import com.application.petcare.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PetRepository extends JpaRepository<Pet, Integer> {
    Optional<List<Pet>> findAllByIdIn(List<Integer> petIds);
}
