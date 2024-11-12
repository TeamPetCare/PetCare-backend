package com.application.petcare.controller.impl;

import com.application.petcare.controller.UserController;
import com.application.petcare.dto.user.*;
import com.application.petcare.entities.User;
import com.application.petcare.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    public ResponseEntity<UserResponse> createUser(UserCreateRequest userCreateRequest) {
        UserResponse createdUser = userService.createUser(userCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(Integer id) {
        UserResponse userResponse = userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.findAllUsersById();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @Override
    public ResponseEntity generateCsvFileCustomerAndPets() {
        // Gerando o CSV com dados mockados
        userService.generateCsvFileCustomerAndPets();

        // Criando um recurso de arquivo para o CSV gerado
        FileSystemResource fileResource = new FileSystemResource(userService.getCsvFilePath());

        // Configurando os cabeçalhos para o download
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"reportCustomersAndPets.csv\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(fileResource);
    }
//    public ResponseEntity<Void> () {
//        userService.generateCsvFileCustomerAndPets();
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

    @Override
    public ResponseEntity<UserResponse> updateUser(Integer id, UserUpdateRequest userUpdateRequest) {
        UserResponse updatedUser = userService.updateUser(id, userUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<List<UserCustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok().body(userService.getAllCustomers());
    }

    @Override
    public ResponseEntity<List<UserCustomerResponse>> getAllCustomersSortedByName() {
        return ResponseEntity.ok().body(userService.getAllCustumersSortedByName());
    }

    @Override
    public ResponseEntity<List<UserEmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok().body(userService.getAllEmployees());
    }

    @Override
    public ResponseEntity<Void> deleteSelectedCustomers(List<UserCustomerDeleteRequest> userCustomerDeleteRequests) {
        userService.deleteSelectedCustomers(userCustomerDeleteRequests);
        return ResponseEntity.noContent().build();
    }

}
