package com.application.petcare.dto.plans;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class PlansResponse {
    private Integer id;
    private Date subscriptionDate;
    private Double priece;
    private Boolean active;
    private int renewal;
    private Integer planTypeId;
    private Integer petId;
}
