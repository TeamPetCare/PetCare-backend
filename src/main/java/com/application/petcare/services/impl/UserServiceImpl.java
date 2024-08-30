package com.application.petcare.services.impl;

import com.application.petcare.entities.User;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(User user) {
        // add lógica de validação aqui
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(UUID id, User updatedUser) {
        User existingUser = findById(id);
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setType(updatedUser.getType());

        return userRepository.save(existingUser);
    }
}