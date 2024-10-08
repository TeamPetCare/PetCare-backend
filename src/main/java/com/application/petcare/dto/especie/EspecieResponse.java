package com.application.petcare.dto.especie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspecieResponse {

    private Integer id;
    private String nomeEspecie;
    private Double preco;
}
