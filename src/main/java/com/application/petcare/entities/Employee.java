package com.application.petcare.entities;

import com.application.petcare.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_employee")
public class Employee extends User {

    @Builder
    public Employee(UUID id, String name, String email, String password, Role type) {
        super(id, name, email, password, type);
    }
}
