package com.application.petcare.services.impl;

import com.application.petcare.dto.user.UserCreateRequest;
import com.application.petcare.dto.user.UserResponse;
import com.application.petcare.dto.user.UserUpdateRequest;
import com.application.petcare.entities.User;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;


    @Override
    public UserResponse createUser(UserCreateRequest request) {

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .street(request.getStreet())
                .number(request.getNumber())
                .complement(request.getComplement())
                .cep(request.getCep())
                .district(request.getDistrict())
                .city(request.getCity()).build();
        User savedUser = repository.save(user);
        return mapToResponse(savedUser);
    }


    @Override
    public UserResponse updateUser(Integer id, UserUpdateRequest request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setStreet(request.getStreet());
        user.setNumber(request.getNumber());
        user.setComplement(request.getComplement());
        user.setCep(request.getCep());
        user.setDistrict(request.getDistrict());
        user.setCity(request.getCity());

        User updatedUser = repository.save(user);
        return mapToResponse(updatedUser);
    }

    @Override
    public UserResponse findUserById(Integer userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return mapToResponse(user);
    }

    @Override
    public List<UserResponse> findAllUsersById() {
        return repository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        repository.deleteById(userId);
    }


    public UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .street(user.getStreet())
                .number(user.getNumber())
                .complement(user.getComplement())
                .cep(user.getCep())
                .district(user.getDistrict())
                .city(user.getCity()).build();
    }
}
