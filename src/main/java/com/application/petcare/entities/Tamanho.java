package com.application.petcare.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_tamanho")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tamanho {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tipo_tamanho", nullable = false)
    private String tipoTamanho;

    @Column(nullable = false)
    private double preco;