package com.application.petcare.services;

import com.application.petcare.dto.login.LoginResponseDto;
import com.application.petcare.dto.user.*;


import java.util.List;

public interface UserService {

    LoginResponseDto createUser(UserCreateRequest request);

    UserResponse updateUser(Integer id, UserUpdateRequest request);

    UserResponse updateUserWithPetList(Integer id, List<Integer> request);

    UserResponse updateCustomer(Integer id, UserCustomerUpdateRequest request);

    UserResponse findUserById(Integer planTypeId);

    List<UserResponse> findAllUsersById();

    byte[] generateCsvFileCustomerAndPets();

    void deleteUser(Integer planTypeId);

    List<UserCustomerResponse> getAllCustomers();

    List<UserAllPlansResponse> getAllCustomersAndPlans();


    List<UserCustomerResponse> getAllCustumersSortedByName();

    List<UserEmployeeResponse> getAllEmployees();

    void deleteSelectedCustomers(List<Integer> userCustomerDeleteRequests);

    UserInfosResponse getUserInfo(Integer id);

    Boolean isCPFUsed(String cpf);
}
