package com.application.petcare.entities;

import com.application.petcare.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_employee")
public class Employee extends User {

    @Builder
    public Employee(UUID id, String name, String email, String password, Role type) {
        super(id, name, email, password, type);
    }
}
