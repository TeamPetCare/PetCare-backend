package com.application.petcare.entities;

import com.application.petcare.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@MappedSuperclass
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public abstract class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Role tipo;
}
