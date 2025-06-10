package com.application.petcare.repository;

import com.application.petcare.entities.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByDeletedAtIsNull();
    List<Notification> findAllByIdInAndDeletedAtIsNull(List<Integer> ids);
    List<Notification> findAllByDeletedAtIsNullAndUser_Id(Integer userId, Pageable pageable);
    List<Notification> findAllByDeletedAtIsNullAndUser_IdAndSawIsFalseOrderByCreatedAtDesc(Integer userId);
    @Modifying
    @Query("UPDATE Notification n SET n.saw = true WHERE n.id IN :ids")
    int markAllAsSawByIds(@Param("ids") List<Integer> ids);
}
