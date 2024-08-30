package com.application.petcare.services;

import com.application.petcare.entities.User;

import java.util.UUID;

public interface UserService {

    User findById(UUID id);

    User findByUsername(String username);

    User createUser(User user);

    void deleteUser(UUID id);

    User updateUser(UUID id, User updatedUser);
}