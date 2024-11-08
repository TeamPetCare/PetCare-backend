package com.application.petcare.dto.notification;

import jakarta.persistence.Column;
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
}
