package com.application.petcare.dto.owner;

import com.application.petcare.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerResponse {

    private Integer id;
    private String name;
    private String cnpj;
    private List<Integer> employeeIds;
}