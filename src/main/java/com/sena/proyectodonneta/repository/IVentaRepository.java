package com.sena.proyectodonneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.proyectodonneta.model.Venta;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Integer> {
}
