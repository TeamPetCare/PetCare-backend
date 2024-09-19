package com.application.petcare.dto.servicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicosResponse {
    private Integer id;
    private String nome;
    private String descricao;
    private Double preco;


}
