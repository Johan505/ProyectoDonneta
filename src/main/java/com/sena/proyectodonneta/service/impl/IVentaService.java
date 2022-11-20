package com.sena.proyectodonneta.service.impl;

import java.util.List;
import java.util.Optional;

import com.sena.proyectodonneta.model.Venta;

public interface IVentaService {
	List<Venta> findAll();

	Optional<Venta> findById(Integer id);

	Venta save(Venta venta);

	String generarNumeroVenta();
}
