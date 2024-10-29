package com.application.petcare.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDeleteRequest {

    private String customer;
    private String  address;
    private Integer id;
    private Integer petQuantity;
    private String cellphone;

}
