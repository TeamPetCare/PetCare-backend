package com.application.petcare.services;

import com.application.petcare.dto.user.*;
import com.application.petcare.entities.User;


import java.util.List;

public interface UserService {

    UserResponse createUser(UserCreateRequest request);

    UserResponse updateUser(Integer id, UserUpdateRequest request);

    UserResponse findUserById(Integer planTypeId);

    List<UserResponse> findAllUsersById();

    void generateCsvFileCustomerAndPets();

    void deleteUser(Integer planTypeId);

    List<UserCustomerResponse> getAllCustomers();

    List<UserCustomerResponse> getAllCustumersSortedByName();

    List<UserEmployeeResponse> getAllEmployees();


    String getCsvFilePath();

    void deleteSelectedCustomers(List<UserCustomerDeleteRequest> userCustomerDeleteRequests);
}
