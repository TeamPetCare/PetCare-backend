package com.application.petcare.dto.plans;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Builder
@Setter
@RequiredArgsConstructor
public class PlansResponse {
    private Integer id;
    private Date subscriptionDate;
    private Double priece;
    private Boolean active;
    private int renewal;
    private Integer planTypeId;
    private Integer petId;
}
