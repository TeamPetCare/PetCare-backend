package com.application.petcare.dto.notification;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {
    private Integer id;
    private String notificationType;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Boolean saw;
    private Integer userId;
}
