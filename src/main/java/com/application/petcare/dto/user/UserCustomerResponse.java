package com.application.petcare.dto.user;

import com.application.petcare.dto.pet.PetPetsListResponse;
import com.application.petcare.entities.Pet;
import com.application.petcare.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserCustomerResponse {
    private Integer id;
    private String name;
    private String userImg;
    private String email;
    private String password;
    private String cellphone;
    private Role role;
    private String street;
    private Integer number;
    private String complement;
    private String cep;
    private String district;
    private String city;
    private String cnpjOwner;
    private String roleEmployee;
    private Boolean disponibilityStatusEmployee;
    private String cpfClient;
    private List<PetPetsListResponse> pet;
    private LocalDateTime lastSchedule;
    private Integer totalSchedules;
}
