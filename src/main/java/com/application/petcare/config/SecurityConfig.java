package com.application.petcare.config;

import com.application.petcare.infra.security.CustomUserDetailsService;
import com.application.petcare.infra.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private SecurityFilter securityFilter;


    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        // Define a hierarquia: OWNER > EMPLOYEE > CUSTOMER
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_EMPLOYEE \n ROLE_EMPLOYEE > ROLE_CUSTOMER");
        return roleHierarchy;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/v2/api-docs",
                                "/swagger-resources/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/webjars/**",
                                "/v3/api-docs/**",
                                "/api/payments/callback"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/swagger-ui/index.html#").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/pets/user/*").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/pets").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/schedules").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/users/*").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PUT, "/api/users/pets").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/services").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/pets/user/*").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/users/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/cpf").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/races").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/species").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/sizes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/pets/data").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/payments").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/plans").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/plans/user/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/plan-types").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/schedules/client/all-time/*").permitAll()
                        .requestMatchers("/api/images/**").permitAll()
                        .requestMatchers("/validate-token").permitAll()
                        .requestMatchers("/api/schedules/**").hasRole("CUSTOMER")
                        .requestMatchers("/api/species/**").hasRole("EMPLOYEE")
                        .requestMatchers("/api/users/**").hasRole("EMPLOYEE")
                        .requestMatchers("/api/notifications/**").hasRole("EMPLOYEE")
                        .requestMatchers("/api/payments/**").hasRole("EMPLOYEE")
                        .requestMatchers("/api/pets/**").hasRole("EMPLOYEE")
                        .requestMatchers("/api/plans/**").hasRole("EMPLOYEE")
                        .requestMatchers("/api/plan-types/**").hasRole("EMPLOYEE")
                        .requestMatchers("/api/races/**").hasRole("EMPLOYEE")
                        .requestMatchers("/api/services/**").hasRole("EMPLOYEE")
                        .requestMatchers("/api/species/**").hasRole("EMPLOYEE")
                        .requestMatchers("/api/sizes/**").hasRole("EMPLOYEE")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(false);
        configuration.addAllowedOrigin("*"); // Permitir o frontend
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
