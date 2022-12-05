package com.sena.proyectodonneta.service.impl;

import java.util.List;
import java.util.Optional;

import com.sena.proyectodonneta.model.Venta;

public interface IVentaService {
	List<Venta> findAll();

	Optional<Venta> findById(Long id);

	Venta save(Venta venta);

	String generarNumeroVenta();
}
