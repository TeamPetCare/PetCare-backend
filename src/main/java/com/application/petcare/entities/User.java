package com.application.petcare.entities;

import com.application.petcare.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_pet")
@Builder
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private String complement;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String city;


    protected User(Integer id, String name, String email, String password, Role role, String street, Integer number, String complement, String cep, String district, String city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.cep = cep;
        this.district = district;
        this.city = city;
    }

}
