package com.application.petcare.dto.plans;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class PlansResponse {
    private Integer id;
    private LocalDateTime subscriptionDate;
    private String name;
    private Double price;
    private Boolean active;
    private int renewal;
    private String description;
    private Integer planTypeId;
    private List<Integer> servicesIds;
    private List<Integer> repeatQuantity;
    private List<Integer> paymentIds;
}
