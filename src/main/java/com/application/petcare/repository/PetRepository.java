package com.application.petcare.repository;

import com.application.petcare.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PetRepository extends JpaRepository<Pet, Integer> {
}
