package com.application.petcare.repository;

import com.application.petcare.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findAllByIdInAndDeletedAtIsNull(List<Integer> petIds);
    List<Pet> findAllByUserIdInAndDeletedAtIsNull(Integer userId);
    List<Pet> findAllByDeletedAtIsNull();
}
