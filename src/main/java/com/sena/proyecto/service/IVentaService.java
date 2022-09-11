package com.sena.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.sena.proyecto.model.Venta;
import com.sena.proyecto.model.Cliente;

public interface IVentaService {
	List<Venta> findAll();
	Optional<Venta> findById(Integer id);
	Venta save (Venta venta);
	String generarNumeroVenta();
}
