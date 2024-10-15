package com.application.petcare.entities;

import com.application.petcare.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "tb_owner")
@Data
@NoArgsConstructor
public class Owner extends Person {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")  // Define o mapeamento do relacionamento
    private List<Employee> employees;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Builder
    public Owner(Integer id, String name,  String cnpj, List<Employee> employees) {
        super(id, name);
        this.cnpj = cnpj;
        this.employees = employees;
    }
}
