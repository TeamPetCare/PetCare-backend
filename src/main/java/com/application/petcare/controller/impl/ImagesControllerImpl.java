package com.application.petcare.controller.impl;

import com.application.petcare.utils.ImageDatabase;
import com.application.petcare.controller.ImagesController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class ImagesControllerImpl implements ImagesController {

    private final ImageDatabase imageDatabase;

    @Override
    public ResponseEntity<Void> uploadImage(MultipartFile file, Integer userId, Boolean isUser) {
        try {
            imageDatabase.uploadImagem(file, userId, isUser);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<String> downloadImage(Integer userId, Boolean isUser) {
        return ResponseEntity.ok().body(imageDatabase.downloadImagem(userId, isUser));
    }
}
