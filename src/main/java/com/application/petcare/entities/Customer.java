package com.application.petcare.entities;

import com.application.petcare.enums.Role;
import jakarta.persistence.*;

import java.util.List;

import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer extends User {


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Builder
    public Customer(UUID id, String name, String email, String password, Role type, List<Pet> pets, String cpf) {
        super(id, name, email, password, type);
        this.pets = pets;
        this.cpf = cpf;
    }
}
