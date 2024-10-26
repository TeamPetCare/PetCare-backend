package com.application.petcare.dto.plans;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class PlansResponse {
    private Integer id;
    private LocalDateTime subscriptionDate;
    private Double priece;
    private Boolean active;
    private int renewal;
    private Integer planTypeId;
}
