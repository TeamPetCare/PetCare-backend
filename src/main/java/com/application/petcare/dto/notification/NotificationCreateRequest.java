package com.application.petcare.dto.notification;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationCreateRequest {
    @NotBlank(message = "Notification type is required")
    private String notificationType;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
    @NotNull(message = "Have been saw? is required")
    private Boolean saw;
    @NotNull(message = "Notification date is required")
    private Integer userId;
}
