package com.application.petcare.repository;

import com.application.petcare.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    List<Size> findAllByDeletedAtIsNull();
}
