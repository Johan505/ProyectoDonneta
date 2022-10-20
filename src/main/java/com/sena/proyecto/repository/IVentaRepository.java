package com.sena.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.proyecto.model.Venta;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Integer> {
}
