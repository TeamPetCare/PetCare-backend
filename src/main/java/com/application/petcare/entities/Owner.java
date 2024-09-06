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
import java.util.UUID;

@Entity
@Table(name = "tb_owner")
@Data
@NoArgsConstructor
public class Owner extends User {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "owner_id")
    private List<Employee> employees;

    @Builder
    public Owner(UUID id, String name, String email, String password, Role type, List<Employee> employees) {
        super(id, name, email, password, type);
        this.employees = employees;
    }
}
