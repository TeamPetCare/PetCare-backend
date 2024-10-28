package com.application.petcare.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public interface ImagesController {

    @Operation(summary = "Criar uma nova imagem")
    @PostMapping("/upload")
    ResponseEntity<Void> uploadImage(@RequestParam ("file") MultipartFile file, @RequestParam Integer userId);

    @Operation(summary = "Buscar uma imagem pelo id do usuario")
    @GetMapping("download/{id}")
    ResponseEntity<byte[]> downloadImage(@PathVariable Integer id);
}
