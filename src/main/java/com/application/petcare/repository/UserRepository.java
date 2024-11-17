package com.application.petcare.repository;

import com.application.petcare.entities.User;
import com.application.petcare.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail (String email);
    List<User> findByRole(Role role);
    List<User> findByRoleNot(Role role);
}
