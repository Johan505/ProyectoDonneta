package com.sena.proyectodonneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.proyectodonneta.model.Detalle;

@Repository
public interface IDetalleRepository extends JpaRepository<Detalle, Long> {

}
