package com.application.petcare.repository;

import com.application.petcare.entities.Servicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servicos, Integer> {
}
