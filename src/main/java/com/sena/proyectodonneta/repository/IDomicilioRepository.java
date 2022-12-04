package com.sena.proyectodonneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.proyectodonneta.model.Domicilio;

@Repository
public interface IDomicilioRepository extends JpaRepository<Domicilio, Long> {

}
