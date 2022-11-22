package com.sena.proyectodonneta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.proyectodonneta.model.User;

public interface UserFindClienteService extends JpaRepository<User, Integer>{

    @Query(value = "SELECT users.id, users.name, users.email, users.password, roles.name FROM users INNER JOIN users_roles ON users_roles.user_id=users.id INNER JOIN roles ON roles.id=users_roles.role_id WHERE roles.name='ROLE_USER'", nativeQuery = true)
    public List <User> obtenerCli();
    
}
