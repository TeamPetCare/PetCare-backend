package com.application.petcare.repository;

import com.application.petcare.entities.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TamanhoRepository extends JpaRepository<Tamanho, UUID> {
}