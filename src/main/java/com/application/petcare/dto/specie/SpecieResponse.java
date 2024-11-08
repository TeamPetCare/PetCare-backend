package com.application.petcare.dto.specie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecieResponse {

    private Integer id;
    private String name;
    private Double price;
}
