package com.application.petcare.services.impl;

import com.application.petcare.dto.pet.PetPetsListResponse;
import com.application.petcare.dto.user.*;
import com.application.petcare.entities.Pet;
import com.application.petcare.entities.User;
import com.application.petcare.enums.Role;
import com.application.petcare.exceptions.DuplicateEntryFoundException;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.PetRepository;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.services.PetService;
import com.application.petcare.services.UserService;
import com.application.petcare.utils.ImageDatabase;
import com.application.petcare.utils.ListaObj;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import com.opencsv.CSVWriter;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PetRepository petRepository;
    private final PetServiceImpl petServiceImpl;

    private final PasswordEncoder passwordEncoder;

    private final ImageDatabase imageDatabase;

    private PetService petService;

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        User user = User.builder()
                .name(request.getName())
                .userImg(request.getUserImg())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
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
                .build();

        if (user.getPet().isEmpty()) {
            throw new ResourceNotFoundException("Pets not found");
        }
        User existingUser = repository.findByEmail(user.getEmail()).get();
        if (user.getEmail().equals(existingUser.getEmail())){
            throw new DuplicateEntryFoundException("Email is already used by another user");
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
        user.setPassword(passwordEncoder.encode(request.getPassword()));
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

        if (user.getPet().isEmpty()) {
            throw new ResourceNotFoundException("Pets not found");
        }

        User updatedUser = repository.save(user);
        return mapToResponse(updatedUser);
    }

    @Override
    public UserResponse updateCustomer(Integer id, UserCustomerUpdateRequest request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        user.setName(request.getCliente());
        user.setCellphone(request.getWhatsApp());
        user.setStreet(request.getRua());
        user.setNumber(request.getNumero());
        user.setComplement(request.getComplemento());
        user.setCep(request.getCep());
        user.setDistrict(request.getBairro());

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

    @Override
    public byte[] generateCsvFileCustomerAndPets() {
        List<User> list = repository.findAll();
        return writeCsvFileCustomerAndPets(list);
    }


    @Override
    public void deleteUser(Integer userId) {
        if (!repository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
        repository.deleteById(userId);
    }

    public List<UserCustomerResponse> getAllCustomers() {
        return mapToUserCustomerResponse(repository.findByRole(Role.ROLE_CUSTOMER));
    }

    public List<UserCustomerResponse> getAllCustumersSortedByName() {
        ListaObj<UserCustomerResponse> listaObj = new ListaObj<>(getAllCustomers().size());
        listaObj.addAll(getAllCustomers());
        mergeSortByName(listaObj, 0, listaObj.getTamanho() - 1);

        return listaObj.convertToList();
    }

    @Override
    public List<UserEmployeeResponse> getAllEmployees() {
        return mapToUserEmployeeResponse(repository.findByRole(Role.ROLE_EMPLOYEE));
    }

    @Override
    public void deleteSelectedCustomers(List<UserCustomerDeleteRequest> userCustomerDeleteRequests) {
        for (int i = 0; i < userCustomerDeleteRequests.size(); i++) {
            UserResponse userResponse = findUserById(userCustomerDeleteRequests.get(i).getId());
            for (int j = 0; j < userResponse.getPetIds().size(); j++) {
                petService.deletePet(userResponse.getPetIds().get(i));
            }
            deleteUser(userCustomerDeleteRequests.get(i).getId());
        }
    }

    @Override
    public UserInfosResponse getUserInfo(Integer id) {
        User possibleUser = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return null;
    }

    private void mergeSortByName(ListaObj<UserCustomerResponse> lista, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSortByName(lista, inicio, meio);
            mergeSortByName(lista, meio + 1, fim);
            merge(lista, inicio, meio, fim);
        }
    }

    private void merge(ListaObj<UserCustomerResponse> lista, int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;
        UserCustomerResponse[] leftArray = new UserCustomerResponse[n1];
        UserCustomerResponse[] rightArray = new UserCustomerResponse[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = lista.getElemento(inicio + i);
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = lista.getElemento(meio + 1 + j);
        }

        int i = 0, j = 0;
        int k = inicio;

        while (i < n1 && j < n2) {
            if (leftArray[i].getName().compareToIgnoreCase(rightArray[j].getName()) <= 0) {
                lista.set(k, leftArray[i++]);
            } else {
                lista.set(k, rightArray[j++]);
            }
            k++;
        }

        while (i < n1) lista.set(k++, leftArray[i++]);

        while (j < n2) lista.set(k++, rightArray[j++]);
    }

    private UserInfosResponse mapToUserInfoResponse(User user){
        return UserInfosResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .userImage(imageDatabase.downloadImagem(user.getId(), true))
                .build();
    }

    private List<UserEmployeeResponse> mapToUserEmployeeResponse(List<User> users){
        List<UserEmployeeResponse> userEmployeeResponses = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            userEmployeeResponses.add(UserEmployeeResponse.builder()
                    .id(users.get(i).getId())
                    .name(users.get(i).getName())
                    .userImg(imageDatabase.downloadImagem(users.get(i).getId(), true))
                    .email(users.get(i).getEmail())
                    .password(users.get(i).getPassword())
                    .cellphone(users.get(i).getCellphone())
                    .role(users.get(i).getRole())
                    .street(users.get(i).getStreet())
                    .number(users.get(i).getNumber())
                    .complement(users.get(i).getComplement())
                    .cep(users.get(i).getCep())
                    .district(users.get(i).getDistrict())
                    .city(users.get(i).getCity())
                    .roleEmployee(users.get(i).getRoleEmployee())
                    .disponibilityStatusEmployee(users.get(i).getDisponibilityStatusEmployee())
                    .build());
        }
        return userEmployeeResponses;
    }

    public List<UserCustomerResponse> mapToUserCustomerResponse(List<User> user){
        List<UserCustomerResponse> userCustomerResponses = new ArrayList<>();
        for (int i = 0; i < user.size(); i++) {
            List<PetPetsListResponse> petPetsListResponses = petServiceImpl.maptoPetPetsListResponse(user.get(i).getPet());
            petPetsListResponses.sort(Comparator.comparing(PetPetsListResponse::getLastSchedule).reversed());
            LocalDateTime lastSchedule;
            if(petPetsListResponses.size() >= 1){
                 lastSchedule = petPetsListResponses.get(0).getLastSchedule();
            }else{
                 lastSchedule = null;
            }


            userCustomerResponses.add(UserCustomerResponse.builder()
                    .id(user.get(i).getId())
                    .name(user.get(i).getName())
                    .userImg(imageDatabase.downloadImagem(user.get(i).getId(), true))
                    .email(user.get(i).getEmail())
                    .password(user.get(i).getPassword())
                    .cellphone(user.get(i).getCellphone())
                    .role(user.get(i).getRole())
                    .street(user.get(i).getStreet())
                    .number(user.get(i).getNumber())
                    .complement(user.get(i).getComplement())
                    .cep(user.get(i).getCep())
                    .district(user.get(i).getDistrict())
                    .city(user.get(i).getCity())
                    .cnpjOwner(user.get(i).getCnpjOwner())
                    .roleEmployee(user.get(i).getRoleEmployee())
                    .disponibilityStatusEmployee(user.get(i).getDisponibilityStatusEmployee())
                    .cpfClient(user.get(i).getCpfClient())
                    .pet(petServiceImpl.maptoPetPetsListResponse(user.get(i).getPet()))
                    .lastSchedule(lastSchedule)
                    .totalSchedules(petPetsListResponses.stream().mapToInt(PetPetsListResponse::getTotalSchedules).sum())
                    .build());
        }
        return userCustomerResponses;
    }

    public UserResponse mapToResponse(User user) {

        List<Integer> petIds = new ArrayList<>();
        for (int i = 0; i < user.getPet().size(); i++) {
            petIds.add(user.getPet().get(i).getId());
        }

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .userImg(imageDatabase.downloadImagem(user.getId(), true))
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

    private byte[] writeCsvFileCustomerAndPets(List<User> lista) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream),
                ';', // Delimitador
                CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {


            // Cabeçalho do CSV
            String[] header = { "ID", "Nome", "Email", "Celular", "Rua", "Número", "Complemento", "CEP", "Bairro", "Cidade", "CPF Cliente", "Nome do Pet", "Raça", "Nascimento", "Porte", "Observações" };
            writer.writeNext(header);

            // Iteração sobre os clientes e seus pets
            for (User userResponse : lista) {
                if (userResponse.getPet() != null && !userResponse.getPet().isEmpty()) {
                    // Itera sobre cada pet do cliente
                    for (Pet pet : userResponse.getPet()) {
                        String[] record = {
                                String.valueOf(userResponse.getId()),
                                userResponse.getName(),
                                userResponse.getEmail(),
                                userResponse.getCellphone(),
                                userResponse.getStreet(),
                                String.valueOf(userResponse.getNumber()),
                                userResponse.getComplement(),
                                userResponse.getCep(),
                                userResponse.getDistrict(),
                                userResponse.getCity(),
                                userResponse.getCpfClient(),
                                pet.getName(),
                                pet.getRace().getRaceType(),
                                pet.getBirthdate().toString(),
                                pet.getSize().getSizeType(),
                                pet.getPetObservations()
                        };
                        writer.writeNext(record);
                    }
                } else {
                    // Caso o cliente não tenha pets, preenche apenas os dados do cliente
                    String[] record = {
                            String.valueOf(userResponse.getId()),
                            userResponse.getName(),
                            userResponse.getEmail(),
                            userResponse.getCellphone(),
                            userResponse.getStreet(),
                            String.valueOf(userResponse.getNumber()),
                            userResponse.getComplement(),
                            userResponse.getCep(),
                            userResponse.getDistrict(),
                            userResponse.getCity(),
                            userResponse.getCpfClient(),
                            "", "", "", "", ""
                    };
                    writer.writeNext(record);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return byteArrayOutputStream.toByteArray();  // Retorna o CSV como um array de bytes
    }



}

