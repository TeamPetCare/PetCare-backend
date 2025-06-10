package com.application.petcare.controller.impl;

import com.application.petcare.controller.NotificationController;
import com.application.petcare.dto.notification.NotificationCreateRequest;
import com.application.petcare.dto.notification.NotificationResponse;
import com.application.petcare.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor

public class NotificationControllerImpl implements NotificationController {

    private NotificationService notificationService;

    @Override
    public ResponseEntity<NotificationResponse> createNotification(NotificationCreateRequest request) {
        return ResponseEntity.status(201).body(notificationService.createNotification(request));
    }

    @Override
    public ResponseEntity<NotificationResponse> updateNotification(Integer id, NotificationCreateRequest request) {
        return ResponseEntity.ok().body(notificationService.updateNotification(id, request));
    }

    @Override
    public ResponseEntity<List<NotificationResponse>> updateSawStatus(List<Integer> ids) {
        return ResponseEntity.ok().body(notificationService.updateSawStatus(ids));
    }

    @Override
    public ResponseEntity<NotificationResponse> getNotificationById(Integer id) {
        return ResponseEntity.ok().body(notificationService.getNotificationById(id));
    }

    @Override
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        return ResponseEntity.ok().body(notificationService.getAllNotifications());
    }

    @Override
    public ResponseEntity<List<NotificationResponse>> getAllUserNotifications(Integer id, Pageable pageable) {
        return ResponseEntity.ok().body(notificationService.getAllUserNotifications(id, pageable));
    }

    @Override
    public ResponseEntity<List<NotificationResponse>> getAllNotReadedUserNotifications(Integer id) {
        return ResponseEntity.ok().body(notificationService.getAllNotReadedUserNotifications(id));
    }

    @Override
    public ResponseEntity<Void> deleteNotification(Integer id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
