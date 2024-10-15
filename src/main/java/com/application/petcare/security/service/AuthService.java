package com.application.petcare.security.service;

import com.application.petcare.dto.security.AuthenticationRequest;
import com.application.petcare.dto.security.AuthenticationResponse;
import com.application.petcare.entities.User;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.repository.UserTokenRepository;
import com.application.petcare.security.model.UserTokenModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public final AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        User user = userRepository.findByUsername(authenticationRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwtToken = jwtService.generateToken(user);
        revokeAllUserToken(user);

        userRepository.save(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();

    }

    private void revokeAllUserToken(User user) {
        List<UserTokenModel> validAccountTokens = userTokenRepository.findAllValidTokenByUser(user.getId());

        if(validAccountTokens.isEmpty()) return;

        validAccountTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        userTokenRepository.saveAll(validAccountTokens);
    }


}
