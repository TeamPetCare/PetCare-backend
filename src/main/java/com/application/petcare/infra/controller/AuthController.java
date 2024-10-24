package com.application.petcare.infra.controller;

import com.application.petcare.dto.login.LoginRequestDto;
import com.application.petcare.dto.login.LoginResponseDto;
import com.application.petcare.dto.register.RegisterRequestDto;
import com.application.petcare.entities.User;
import com.application.petcare.infra.security.TokenService;
import com.application.petcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok().body(new LoginResponseDto(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> register(@RequestBody RegisterRequestDto body){
        Optional<User> possibleUser = this.repository.findByEmail(body.email());
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

            this.repository.save(newUser);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok().body(new LoginResponseDto(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
