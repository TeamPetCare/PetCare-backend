package com.application.petcare.services;

import com.application.petcare.dto.notification.NotificationCreateRequest;
import com.application.petcare.dto.notification.NotificationResponse;

import java.util.List;

public interface NotificationService {
    NotificationResponse createNotification(NotificationCreateRequest request);

    NotificationResponse updateNotification(Integer id, NotificationCreateRequest request);

    NotificationResponse getNotificationById(Integer id);

    List<NotificationResponse> getAllNotifications();

    List<NotificationResponse> getAllUserNotifications(Integer id);

    void deleteNotification(Integer id);
}
