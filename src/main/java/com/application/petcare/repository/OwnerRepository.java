package com.application.petcare.repository;

import com.application.petcare.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OwnerRepository extends JpaRepository<Owner, UUID> {
}
