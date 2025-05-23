package com.application.petcare.dto.plans;

import com.application.petcare.entities.PlanType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class UserPlansResponse {
    private Integer id;
    private Boolean active;
    private String planTypeName;
    private List<String> petNames;
}
