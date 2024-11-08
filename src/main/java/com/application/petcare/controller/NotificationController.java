package com.application.petcare.controller;


import com.application.petcare.dto.notification.NotificationCreateRequest;
import com.application.petcare.dto.notification.NotificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Notifications", description = "Gerenciar notificações")
@RequestMapping("/api/notifications")
public interface NotificationController {
    @Operation(summary = "Criar uma nova notificação")
    @PostMapping
    ResponseEntity<NotificationResponse> createNotification(@RequestBody NotificationCreateRequest request);

    @Operation(summary = "Atualizar uma notificação existente")
    @PutMapping("/{id}")
    ResponseEntity<NotificationResponse> updateNotification(@PathVariable Integer id, @RequestBody NotificationCreateRequest request);

    @Operation(summary = "Buscar uma notificação pelo ID")
    @GetMapping("/{id}")
    ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Integer id);

    @Operation(summary = "Listar todas as notificações")
    @GetMapping
    ResponseEntity<List<NotificationResponse>> getAllNotifications();

    @Operation(summary = "Deletar uma notificação")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteNotification(@PathVariable Integer id);
}
