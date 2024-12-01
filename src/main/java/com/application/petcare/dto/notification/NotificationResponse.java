package com.application.petcare.dto.notification;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {
    private String notificationType;
    private String title;
    private String description;
    private LocalDateTime notificationDate;
    private Boolean saw;
    private Integer userId;
}
