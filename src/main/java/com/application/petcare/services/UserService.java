package com.application.petcare.services;

import com.application.petcare.dto.user.*;
import com.application.petcare.entities.User;


import java.util.List;

public interface UserService {

    UserResponse createUser(UserCreateRequest request);

    UserResponse updateUser(Integer id, UserUpdateRequest request);

    UserResponse updateCustomer(Integer id, UserCustomerUpdateRequest request);

    UserResponse findUserById(Integer planTypeId);

    List<UserResponse> findAllUsersById();

    byte[] generateCsvFileCustomerAndPets();

    void deleteUser(Integer planTypeId);

    List<UserCustomerResponse> getAllCustomers();

    List<UserPlansResponse> getAllCustomersAndPlans();


    List<UserCustomerResponse> getAllCustumersSortedByName();

    List<UserEmployeeResponse> getAllEmployees();

    void deleteSelectedCustomers(List<Integer> userCustomerDeleteRequests);

    UserInfosResponse getUserInfo(Integer id);
}
