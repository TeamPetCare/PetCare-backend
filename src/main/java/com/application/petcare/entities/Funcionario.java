package com.application.petcare.entities;

import com.application.petcare.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@AllArgsConstructor
@Entity
@Table(name = "tb_funcionario")
public class Funcionario extends Usuario {

    @Builder
    public Funcionario(UUID id, String nome, String email, String senha, Role tipo) {
        super(id, nome, email, senha, tipo);
    }


}
