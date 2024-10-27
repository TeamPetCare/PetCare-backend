package com.application.petcare.services.impl;

import com.application.petcare.dto.user.UserCreateRequest;
import com.application.petcare.dto.user.UserResponse;
import com.application.petcare.dto.user.UserUpdateRequest;
import com.application.petcare.entities.User;
import com.application.petcare.enums.Role;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.PetRepository;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PetRepository petRepository;

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        User user = User.builder()
                .name(request.getName())
                .userImg(request.getUserImg())
                .email(request.getEmail())
                .password(request.getPassword())
                .cellphone(request.getCellphone())
                .role(request.getRole())
                .street(request.getStreet())
                .number(request.getNumber())
                .complement(request.getComplement())
                .cep(request.getCep())
                .district(request.getDistrict())
                .city(request.getCity())
                .cnpjOwner(request.getCnpjOwner())
                .roleEmployee(request.getRoleEmployee())
                .disponibilityStatusEmployee(request.getDisponibilityStatus())
                .cpfClient(request.getCpfClient())
                .pet(petRepository.findAllByIdIn(request.getPetIds()))
                .build()
                ;

        if(user.getPet().isEmpty()){
            throw new ResourceNotFoundException("Pets not found");
        }
        User savedUser = repository.save(user);
        return mapToResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Integer id, UserUpdateRequest request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        user.setName(request.getName());
        user.setUserImg(request.getUserImg());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCellphone(request.getCellphone());
        user.setRole(request.getRole());
        user.setStreet(request.getStreet());
        user.setNumber(request.getNumber());
        user.setComplement(request.getComplement());
        user.setCep(request.getCep());
        user.setDistrict(request.getDistrict());
        user.setCity(request.getCity());
        user.setCnpjOwner(request.getCnpjOwner());
        user.setRoleEmployee(request.getRoleEmployee());
        user.setDisponibilityStatusEmployee(request.getDisponibilityStatus());
        user.setCpfClient(request.getCpfClient());
        user.setPet(petRepository.findAllByIdIn(request.getPetIds()));

        if(user.getPet().isEmpty()){
            throw new ResourceNotFoundException("Pets not found");
        }

        User updatedUser = repository.save(user);
        return mapToResponse(updatedUser);
    }

    @Override
    public UserResponse findUserById(Integer userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        return mapToResponse(user);
    }

    @Override
    public List<UserResponse> findAllUsersById() {
        return repository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public void generateCsvFileCustomer() {
        List<UserResponse> list = repository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
        writeCsvFileCustomer(list, "reportCustomers");
    }


    @Override
    public void deleteUser(Integer userId) {
        if (!repository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
        repository.deleteById(userId);
    }

    public List<User> getAllCustomers(){
        return repository.findByRole(Role.ROLE_CUSTOMER);
    }

    public UserResponse mapToResponse(User user) {

        List<Integer> petIds = new ArrayList<>();
        for (int i = 0; i < user.getPet().size(); i++) {
        petIds.add(user.getPet().get(i).getId());
        }

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .userImg(user.getUserImg())
                .email(user.getEmail())
                .password(user.getPassword())
                .cellphone(user.getCellphone())
                .role(user.getRole())
                .street(user.getStreet())
                .number(user.getNumber())
                .complement(user.getComplement())
                .cep(user.getCep())
                .district(user.getDistrict())
                .city(user.getCity())
                .cnpjOwner(user.getCnpjOwner())
                .roleEmployee(user.getRoleEmployee())
                .disponibilityStatus(user.getDisponibilityStatusEmployee())
                .cpfClient(user.getCpfClient())
                .petIds(petIds)
                .build();
    }

    public static void writeCsvFileCustomer(List<UserResponse> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);

        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();
            System.exit(1);
        }

        try {
            for (UserResponse userResponse : lista) {
                saida.format("%d;%s;%s;%s;%s;%d;%s;%s;%s;%s;%s\n",
                        userResponse.getId(),
                        userResponse.getName(),
                        userResponse.getEmail(),
                        userResponse.getCellphone(),
                        userResponse.getStreet(),
                        userResponse.getNumber(),
                        userResponse.getComplement(),
                        userResponse.getCep(),
                        userResponse.getDistrict(),
                        userResponse.getCity(),
                        userResponse.getCpfClient());
            }

            System.out.println("Arquivo CSV salvo em: " + new File(nomeArq).getAbsolutePath());
        }
        catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }



}
