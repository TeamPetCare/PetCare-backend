package com.application.petcare.controller.impl;

import com.application.petcare.controller.UserController;
import com.application.petcare.dto.user.UserCreateRequest;
import com.application.petcare.dto.user.UserResponse;
import com.application.petcare.dto.user.UserUpdateRequest;
import com.application.petcare.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<UserResponse> updateUser(Integer id, UserUpdateRequest userUpdateRequest) {
        UserResponse updatedUser = userService.updateUser(id, userUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
