package com.sena.proyectodonneta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.proyectodonneta.model.Domicilio;

public interface DomicilioIdRepository extends JpaRepository<Domicilio, Long>{

    @Query(value = "SELECT*FROM domicilios INNER JOIN users on users.id=domicilios.user_id WHERE users.id = ?1", nativeQuery = true)
  public List <Domicilio> findDomiciliario(Long id);
    
}
