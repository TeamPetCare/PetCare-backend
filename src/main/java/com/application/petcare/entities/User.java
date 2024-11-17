package com.application.petcare.entities;

import com.application.petcare.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Collection;
import java.util.List;

@Table(name = "Users")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String userImg;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String cellphone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = true)
    private String complement;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String city;

    @CNPJ
    private String cnpjOwner;

    private String roleEmployee;

    private Boolean disponibilityStatusEmployee;

    @CPF
    private String cpfClient;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Pet> pet;


}
