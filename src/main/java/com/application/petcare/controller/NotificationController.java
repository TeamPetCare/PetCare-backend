package com.application.petcare.controller;


import com.application.petcare.dto.notification.NotificationCreateRequest;
import com.application.petcare.dto.notification.NotificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @Operation(summary = "Atualizar uma lista de notificação existente")
    @PutMapping("/saw")
    ResponseEntity<List<NotificationResponse>> updateSawStatus(@RequestParam List<Integer> ids);

    @Operation(summary = "Buscar uma notificação pelo ID")
    @GetMapping("/{id}")
    ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Integer id);

    @Operation(summary = "Listar todas as notificações")
    @GetMapping
    ResponseEntity<List<NotificationResponse>> getAllNotifications();

    @Operation(summary = "Listar todas as notificações de um usuário especifico")
    @GetMapping("/user/{id}")
    ResponseEntity<List<NotificationResponse>> getAllUserNotifications(
            @PathVariable Integer id,
            @PageableDefault(size = 7, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable);

    @Operation(summary = "Listar todas as notificações não vistas de um usuário especifico")
    @GetMapping("/user/not-readed/{id}")
    ResponseEntity<List<NotificationResponse>> getAllNotReadedUserNotifications(@PathVariable Integer id);

    @Operation(summary = "Deletar uma notificação")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteNotification(@PathVariable Integer id);
}
