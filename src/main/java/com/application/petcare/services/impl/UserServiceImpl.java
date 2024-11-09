package com.application.petcare.services.impl;

import com.application.petcare.dto.user.CustomerDeleteRequest;
import com.application.petcare.dto.user.UserCreateRequest;
import com.application.petcare.dto.user.UserResponse;
import com.application.petcare.dto.user.UserUpdateRequest;
import com.application.petcare.entities.Pet;
import com.application.petcare.entities.User;
import com.application.petcare.enums.Role;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.PetRepository;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.services.PetService;
import com.application.petcare.services.UserService;
import com.application.petcare.utils.ListaObj;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    private static final String CSV_FILE_PATH = "reportCustomersAndPets.csv";

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
    public UserResponse findUserById(Integer userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        return mapToResponse(user);
    }

    @Override
    public List<UserResponse> findAllUsersById() {
        return repository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public void generateCsvFileCustomerAndPets() {
        List<User> list = repository.findAll();
        writeCsvFileCustomerAndPets(list, "reportCustomersAndPets");
    }


    @Override
    public void deleteUser(Integer userId) {
        if (!repository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
        repository.deleteById(userId);
    }

    public List<User> getAllCustomers() {
        return repository.findByRole(Role.ROLE_CUSTOMER);
    }

    public List<User> getAllCustumersSortedByName() {
        ListaObj<User> listaObj = new ListaObj<>(getAllCustomers().size());
        listaObj.addAll(getAllCustomers());
        mergeSortByName(listaObj, 0, listaObj.getTamanho() - 1);

        return listaObj.convertToList();
    }

    @Override
    public void deleteSelectedCustomers(List<CustomerDeleteRequest> customerDeleteRequests) {
        for (int i = 0; i < customerDeleteRequests.size(); i++) {
            UserResponse userResponse = findUserById(customerDeleteRequests.get(i).getId());
            for (int j = 0; j < userResponse.getPetIds().size(); j++) {
                petService.deletePet(userResponse.getPetIds().get(i));
            }
            deleteUser(customerDeleteRequests.get(i).getId());
        }
    }

    private void mergeSortByName(ListaObj<User> lista, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSortByName(lista, inicio, meio);
            mergeSortByName(lista, meio + 1, fim);
            merge(lista, inicio, meio, fim);
        }
    }

    private void merge(ListaObj<User> lista, int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;
        User[] leftArray = new User[n1];
        User[] rightArray = new User[n2];

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

    public static void writeCsvFileCustomerAndPets(List<User> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        boolean deuRuim = false;

        nomeArq += ".csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);

            // Cabeçalho do CSV
            saida.format("ID;Nome;Email;Celular;Rua;Número;Complemento;CEP;Bairro;Cidade;CPF Cliente;Nome do Pet;Raça;Nascimento;Porte;Observações\n");

            // Iteração sobre os clientes e seus pets
            for (User userResponse : lista) {
                if (userResponse.getPet() != null && !userResponse.getPet().isEmpty()) {
                    // Itera sobre cada pet do cliente
                    for (Pet pet : userResponse.getPet()) {
                        saida.format("%d;%s;%s;%s;%s;%d;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
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
                                userResponse.getCpfClient(),
                                pet.getName(),
                                pet.getRace().getRaceType(),
                                pet.getBirthdate(),
                                pet.getSize().getSizeType(),
                                pet.getPetObservations());
                    }
                } else {
                    // Caso o cliente não tenha pets, preenche apenas os dados do cliente
                    saida.format("%d;%s;%s;%s;%s;%d;%s;%s;%s;%s;%s;;;;;\n",
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
            }

            System.out.println("Arquivo CSV salvo em: " + new File(nomeArq).getAbsolutePath());
        } catch (IOException | FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
            deuRuim = true;
        } finally {
            if (saida != null) saida.close();
            try {
                if (arq != null) arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public String getCsvFilePath() {
        return CSV_FILE_PATH;
    }



}

