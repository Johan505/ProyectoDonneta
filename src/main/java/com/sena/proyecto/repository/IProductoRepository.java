package com.sena.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.proyecto.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

}
