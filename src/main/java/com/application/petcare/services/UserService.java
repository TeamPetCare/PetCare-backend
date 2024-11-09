package com.application.petcare.services;

import com.application.petcare.dto.user.UserCreateRequest;
import com.application.petcare.dto.user.CustomerDeleteRequest;
import com.application.petcare.dto.user.UserResponse;
import com.application.petcare.dto.user.UserUpdateRequest;
import com.application.petcare.entities.User;


import java.util.List;

public interface UserService {

    UserResponse createUser(UserCreateRequest request);

    UserResponse updateUser(Integer id, UserUpdateRequest request);

    UserResponse findUserById(Integer planTypeId);

    List<UserResponse> findAllUsersById();

    void generateCsvFileCustomerAndPets();

    void deleteUser(Integer planTypeId);

    List<User> getAllCustomers();

    List<User> getAllCustumersSortedByName();

    String getCsvFilePath();

    void deleteSelectedCustomers(List<CustomerDeleteRequest> customerDeleteRequests);
}
