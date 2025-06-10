package com.application.petcare.services;

import com.application.petcare.dto.notification.NotificationCreateRequest;
import com.application.petcare.dto.notification.NotificationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NotificationService {
    NotificationResponse createNotification(NotificationCreateRequest request);

    NotificationResponse updateNotification(Integer id, NotificationCreateRequest request);

    List<NotificationResponse> updateSawStatus(List<Integer> ids);

    NotificationResponse getNotificationById(Integer id);

    List<NotificationResponse> getAllNotifications();

    List<NotificationResponse> getAllNotReadedUserNotifications(Integer id);

    List<NotificationResponse> getAllUserNotifications(Integer id, Pageable pageable);

    void deleteNotification(Integer id);
}
