package com.application.petcare.repository;

import com.application.petcare.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByDeletedAtIsNull();
    List<Notification> findAllByDeletedAtIsNullAndUser_Id(Integer userId);
}
