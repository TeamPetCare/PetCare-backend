package com.application.petcare.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "Notification")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String notificationType;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime notificationDate;
}
