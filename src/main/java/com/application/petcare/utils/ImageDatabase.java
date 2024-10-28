package com.application.petcare.utils;

import com.application.petcare.exceptions.BadRequestException;
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.models.BlobProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ImageDatabase {
    // Connection string do Azure Blob Storage
    @Value("${blob.connection.url}")
    private String CONNECTION_STRING;
    // Nome do container no Azure Blob
    private final String CONTAINER_NAME = "imagens";

    public void uploadImagem(MultipartFile file, Integer userId, Boolean isUser) throws IOException {
        // Cria o cliente do container do Blob
        BlobContainerClient containerClient = new BlobContainerClientBuilder()
                .connectionString(CONNECTION_STRING)
                .containerName(CONTAINER_NAME)
                .buildClient();

        String blobName;
        if(isUser){
        // Gera um nome único para a imagem (você pode modificar como desejar)
         blobName = "imagem_usuario_" + userId; // Ou customizar o nome aqui com "usuario_foto_" etc.
        }else{
            blobName = "imagem_pet_" + userId;
        }
        // Obtém o BlobClient para o arquivo específico
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        // Converte o MultipartFile para InputStream

        if(blobClient.equals(null)){
            throw new BadRequestException("isUser is null");
        }

        try (InputStream inputStream = file.getInputStream()) {
            // Faz o upload da imagem para o Blob Storage
            blobClient.upload(inputStream, file.getSize(), true);
            // Define o tipo de conteúdo (Content-Type)
            BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(file.getContentType());
            blobClient.setHttpHeaders(headers);
        }
    }

    public ResponseEntity<byte[]> downloadImagem(Integer userId, Boolean isUser) {
        BlobContainerClient containerClient = new BlobContainerClientBuilder()
                .connectionString(CONNECTION_STRING)
                .containerName(CONTAINER_NAME)
                .buildClient();

        BlobClient blobClient;
        if (isUser) {
            // Obtém o BlobClient para a imagem específica
             blobClient = containerClient.getBlobClient("imagem_usuario_" + userId);
        } else {
             blobClient = containerClient.getBlobClient("imagem_pet_" + userId);
        }

        // Prepara um byte array para armazenar os dados da imagem
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        if(blobClient.equals(null)){
            throw new BadRequestException("isUser is null");
        }

        try (InputStream inputStream = blobClient.openInputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            // Lê os dados do blob e os escreve no outputStream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Define o tipo de conteúdo da imagem
            BlobProperties properties = blobClient.getProperties();
            String contentType = properties.getContentType();

            // Retorna a imagem como um array de bytes
            return ResponseEntity.ok()
                    .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                    .body(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build(); // Retorna um erro interno do servidor
        }
    }
}


