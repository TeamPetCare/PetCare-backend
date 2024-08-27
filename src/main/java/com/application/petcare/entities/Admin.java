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
@Table(name = "tb_admin")
@Data
@NoArgsConstructor
public class Admin extends Usuario {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    @Builder
    public Admin(UUID id, String nome, String email, String senha, Role tipo, List<Funcionario> funcionarios) {
        super(id, nome, email, senha, tipo);
        this.funcionarios = funcionarios;
    }
}
