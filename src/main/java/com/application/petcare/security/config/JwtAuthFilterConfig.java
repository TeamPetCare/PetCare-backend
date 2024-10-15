package com.application.petcare.security.config;

import com.application.petcare.dto.ResponseMessage;
import com.application.petcare.repository.UserTokenRepository;
import com.application.petcare.security.service.JwtService;
import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilterConfig extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final UserTokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            if (isAuthApiRequest(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            final String authHeader = request.getHeader("Authorization");
            if (isInvalidAuthHeader(authHeader)) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwt = extractJwt(authHeader);
            String username = jwtService.extractUsername(jwt);

            if (username != null && isUserNotAuthenticated()) {
                processAuthentication(request, jwt, username);
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException ex) {
            sendErrorResponse(response, ex);
        } catch (IOException | ServletException ex) {
            throw ex;
        }

    }

    private boolean isAuthApiRequest(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/auth/authenticate");
    }

    private String extractJwt(String authHeader) {
        return authHeader.substring(7);
    }

    private boolean isInvalidAuthHeader(String authHeader) {
        return authHeader == null || !authHeader.startsWith("Bearer ");
    }

    private boolean isUserNotAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private void processAuthentication(HttpServletRequest request, String jwt, String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (isTokenValid(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    private boolean isTokenValid(String jwt, UserDetails userDetails) {
        return jwtService.isTokenValid(jwt, userDetails) &&
                tokenRepository.findByToken(jwt)
                        .map(t -> !t.isExpired() && !t.isRevoked())
                        .orElse(false);
    }

    private void sendErrorResponse(HttpServletResponse response, ExpiredJwtException ex) throws IOException {

        Gson gson = new Gson();

        response.setContentType("application/json");
        response.setStatus(HttpStatus.CONFLICT.value());

        ResponseMessage errorMessage = ResponseMessage.builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .message("Token expired")
                .build();


        try {
            response.getWriter().write(gson.toJson(errorMessage));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
