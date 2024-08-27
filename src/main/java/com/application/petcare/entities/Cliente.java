package com.application.petcare.entities;


import com.application.petcare.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_cliente")
@Data
@NoArgsConstructor
public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;

    @Builder
    public Cliente(UUID id, String nome, String email, String senha, Role tipo, List<Pet> pets) {
        super(id, nome, email, senha, tipo);
        this.pets = pets;
    }
}
