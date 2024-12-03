package com.application.petcare.dto.user;

import com.application.petcare.entities.Plans;
import com.application.petcare.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPlansResponse {
    private User user;
    private Plans plans;
}
