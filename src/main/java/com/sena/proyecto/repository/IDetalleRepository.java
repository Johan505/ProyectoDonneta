package com.sena.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.proyecto.model.Detalle;

@Repository
public interface IDetalleRepository extends JpaRepository<Detalle, Integer> {

}
