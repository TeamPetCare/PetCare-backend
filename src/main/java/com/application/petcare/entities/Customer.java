package com.application.petcare.entities;

import com.application.petcare.enums.Role;
import jakarta.persistence.*;

import java.util.List;

import lombok.*;


@Entity
@Table(name = "tb_customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer extends Person {


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Builder
    public Customer(Integer id, String name, List<Pet> pets, String cpf) {
        super(id, name);
        this.pets = pets;
        this.cpf = cpf;
    }
}
