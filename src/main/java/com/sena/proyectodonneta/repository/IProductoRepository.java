package com.sena.proyectodonneta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.proyectodonneta.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

}
