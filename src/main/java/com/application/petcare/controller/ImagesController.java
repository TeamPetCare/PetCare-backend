package com.application.petcare.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "Images", description = "Gerenciar imagens do banco de imagens")
@RequestMapping("/images")
public interface ImagesController {

    @Operation(summary = "Criar uma nova imagem")
    @PostMapping("/upload")
    ResponseEntity<Void> uploadImage(@RequestParam ("file") MultipartFile file, @RequestParam Integer userId, @RequestParam Boolean isUser);

    @Operation(summary = "Buscar uma imagem pelo id do usuario")
    @GetMapping("/download/{userId}")
    ResponseEntity<String> downloadImage(@PathVariable Integer userId, @RequestParam Boolean isUser);
}
