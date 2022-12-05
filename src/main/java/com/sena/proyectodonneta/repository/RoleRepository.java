package com.sena.proyectodonneta.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.proyectodonneta.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
