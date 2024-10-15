package com.application.petcare.entities;

import com.application.petcare.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_employee")
public class Employee extends Person {

    @Column(nullable = false)
    private String cargo;

    @Builder
    public Employee(Integer id, String name, String cargo) {
        super(id, name);
        this.cargo = cargo;
    }
}
