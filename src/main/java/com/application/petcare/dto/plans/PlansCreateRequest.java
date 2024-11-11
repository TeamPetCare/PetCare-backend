package com.application.petcare.dto.plans;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class PlansCreateRequest {
    @NotNull(message = "Subscription date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime subscriptionDate;
    @NotBlank
    private String name;
    @NotNull(message = "Price is required")
    private Double price;
    @NotNull(message = "'Is active?' is required")
    private Boolean active;
    @NotNull(message = "Renewal is required")
    private int renewal;
    @NotBlank(message = "Description is required")
    private String description;
    @NotNull(message = "Plan Type id is required")
    private Integer planTypeId;
    @NotNull(message = "Service ids is required")
    private List<Integer> servicesIds;
    @NotNull(message = "Repeat quantity is required")
    private List<Integer> repeatQuantity;
    @NotNull(message = "Payment Ids is required")
    private List<Integer> paymentIds;
}
