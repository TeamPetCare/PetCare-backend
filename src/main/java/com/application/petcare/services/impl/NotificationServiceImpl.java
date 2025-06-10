package com.application.petcare.services.impl;

import com.application.petcare.dto.notification.NotificationCreateRequest;
import com.application.petcare.dto.notification.NotificationResponse;
import com.application.petcare.entities.Notification;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.NotificationRepository;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
                .deletedAt(null)
                .createdAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
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
        notification.setSaw(request.getSaw());
        notification.setUser(userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found")));

        Notification updatedNotification = notificationRepository.save(notification);
        return mapNotificationToNotificationResponse(updatedNotification);
    }

    @Override
    @Transactional
    public List<NotificationResponse> updateSawStatus(List<Integer> ids) {
        notificationRepository.markAllAsSawByIds(ids);
        return notificationRepository.findAllByIdInAndDeletedAtIsNull(ids).stream().map(this::mapNotificationToNotificationResponse).collect(Collectors.toList());
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
    public List<NotificationResponse> getAllUserNotifications(Integer id, Pageable pageable) {
        return notificationRepository.findAllByDeletedAtIsNullAndUser_Id(id, pageable).stream().map(this::mapNotificationToNotificationResponse).collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponse> getAllNotReadedUserNotifications(Integer id) {
        return notificationRepository.findAllByDeletedAtIsNullAndUser_IdAndSawIsFalseOrderByCreatedAtDesc(id).stream().map(this::mapNotificationToNotificationResponse).collect(Collectors.toList());
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
                .id(request.getId())
                .notificationType(request.getNotificationType())
                .title(request.getTitle())
                .description(request.getDescription())
                .createdAt(request.getCreatedAt())
                .saw(request.getSaw())
                .userId(request.getUser().getId())
                .build();
    }
}
