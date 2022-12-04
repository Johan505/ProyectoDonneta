package com.sena.proyectodonneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.proyectodonneta.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
