package com.application.petcare.entities;

import com.application.petcare.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "tb_customer")
@Data
@NoArgsConstructor
public class Customer extends User {

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;

    @Builder
    public Customer(UUID id, String name, String email, String password, Role type, List<Pet> pets) {
        super(id, name, email, password, type);
        this.pets = pets;
    }
}
