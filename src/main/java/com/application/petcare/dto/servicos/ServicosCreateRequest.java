package com.application.petcare.dto.servicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServicosCreateRequest {

    @NotBlank(message = "Nome serviço é obrigatorio")
    private String nome;

    @NotBlank(message = "Descrição é obrigatorio")
    private String descricao;

    @NotNull(message = "Preço é obrigatorio")
    private Double preco;

}
