package com.application.petcare.services;

import com.application.petcare.dto.user.UserCreateRequest;
import com.application.petcare.dto.user.UserResponse;
import com.application.petcare.dto.user.UserUpdateRequest;


import java.util.List;

public interface UserService {

    UserResponse createUser(UserCreateRequest request);

    UserResponse updateUser(Integer id, UserUpdateRequest request);

    UserResponse findUserById(Integer userId);

    List<UserResponse> findAllUsersById();

    void deleteUser(Integer userId);
}
