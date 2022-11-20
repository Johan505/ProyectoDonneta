package com.sena.proyectodonneta.service;



import java.util.List;
import java.util.Optional;

import com.sena.proyectodonneta.dto.UserDto;
import com.sena.proyectodonneta.model.User;

public interface UserService {
    
    Optional<User> findById(Long id);

    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();

}
