package com.application.petcare.infra.controller;

import com.application.petcare.dto.login.LoginRequestDto;
import com.application.petcare.dto.login.LoginResponseDto;
import com.application.petcare.dto.register.RegisterRequestDto;
import com.application.petcare.entities.User;
import com.application.petcare.enums.Role;
import com.application.petcare.exceptions.BadRoleException;
import com.application.petcare.exceptions.DuplicateEntryFoundException;
import com.application.petcare.infra.security.TokenService;
import com.application.petcare.repository.PetRepository;
import com.application.petcare.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Tag(name = "Auth", description = "Gerenciar autentificacao do security")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PetRepository petRepository;

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto body){
        User user = this.repository.findByEmailAndDeletedAtIsNull(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(user.getRole().equals(Role.ROLE_CUSTOMER)){
            throw new BadRoleException("Customer is not allowed");
        }
        if(passwordEncoder.matches(body.password(), user.getPassword())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok().body(new LoginResponseDto(user.getId(), user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login/customer")
    public ResponseEntity<LoginResponseDto> loginCustomer(@RequestBody LoginRequestDto body){
        User user = this.repository.findByEmailAndDeletedAtIsNull(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(!user.getRole().equals(Role.ROLE_CUSTOMER)){
            throw new BadRoleException("Owner or employee is not allowed");
        }
        if(passwordEncoder.matches(body.password(), user.getPassword())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok().body(new LoginResponseDto(user.getId(), user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> register(@RequestBody RegisterRequestDto body){
        Optional<User> possibleUser = this.repository.findByEmailAndDeletedAtIsNull(body.email());
        if(possibleUser.isEmpty()){
            User newUser = new User();
            newUser.setName(body.name());
            newUser.setUserImg(body.userImg());
            newUser.setEmail(body.email());
            newUser.setPassword(passwordEncoder.encode(body.password())); // Codificando a senha
            newUser.setCellphone(body.cellphone());
            newUser.setRole(body.role());
            newUser.setStreet(body.street());
            newUser.setNumber(body.number());
            newUser.setComplement(body.complement());
            newUser.setCep(body.cep());
            newUser.setDistrict(body.district());
            newUser.setCity(body.city());
            newUser.setCnpjOwner(body.cnpjOwner());
            newUser.setRoleEmployee(body.roleEmployee());
            newUser.setDisponibilityStatusEmployee(body.disponibilityStatus());
            newUser.setCpfClient(body.cpfClient());
            newUser.setPets(petRepository.findAllByIdInAndDeletedAtIsNull(body.petIds()));

            this.repository.save(newUser);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok().body(new LoginResponseDto(newUser.getId(), newUser.getName(), token));
        }
        throw new DuplicateEntryFoundException("Email is already used by another user");
    }
}
