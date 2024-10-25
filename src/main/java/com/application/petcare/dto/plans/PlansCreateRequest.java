package com.application.petcare.dto.plans;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class PlansCreateRequest {
    @NotNull(message = "Subscription date is required")
    private Date subscriptionDate;
    @NotNull(message = "Priece is required")
    private Double priece;
    @NotNull(message = "'Is active?' is required")
    private Boolean active;
    @NotNull(message = "Renewal is required")
    private int renewal;
    @NotNull(message = "Plan Type id is required")
    private Integer planTypeId;
    @NotNull(message = "Pet id is required")
    private Integer petId;
}
