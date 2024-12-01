package com.application.petcare.services.impl;

import com.application.petcare.dto.notification.NotificationCreateRequest;
import com.application.petcare.dto.notification.NotificationResponse;
import com.application.petcare.entities.Notification;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.NotificationRepository;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private UserRepository userRepository;

    @Override
    public NotificationResponse createNotification(NotificationCreateRequest request) {
        Notification notification = Notification.builder()
                .notificationType(request.getNotificationType())
                .title(request.getTitle())
                .description(request.getDescription())
                .notificationDate(request.getNotificationDate())
                .deletedAt(null)
                .saw(false)
                .user(userRepository.findById(request.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found")))
                .build();
        Notification savedNotification = notificationRepository.save(notification);
        return mapNotificationToNotificationResponse(savedNotification);
    }

    @Override
    public NotificationResponse updateNotification(Integer id, NotificationCreateRequest request) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        notification.setNotificationType(request.getNotificationType());
        notification.setTitle(request.getTitle());
        notification.setDescription(request.getDescription());
        notification.setNotificationDate(request.getNotificationDate());
        notification.setSaw(request.getSaw());
        notification.setUser(userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found")));

        Notification updatedNotification = notificationRepository.save(notification);
        return mapNotificationToNotificationResponse(updatedNotification);
    }

    @Override
    public NotificationResponse getNotificationById(Integer id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        return mapNotificationToNotificationResponse(notification);
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAllByDeletedAtIsNull().stream().map(this::mapNotificationToNotificationResponse).toList();
    }

    @Override
    public List<NotificationResponse> getAllUserNotifications(Integer id) {
        return notificationRepository.findAllByDeletedAtIsNullAndUser_Id(id).stream().map(this::mapNotificationToNotificationResponse).collect(Collectors.toList());
    }

    @Override
    public void deleteNotification(Integer id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        notification.setDeletedAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    public NotificationResponse mapNotificationToNotificationResponse(Notification request){
        return NotificationResponse.builder()
                .notificationType(request.getNotificationType())
                .title(request.getTitle())
                .description(request.getDescription())
                .notificationDate(request.getNotificationDate())
                .saw(request.getSaw())
                .userId(request.getUser().getId())
                .build();
    }
}
