package com.sena.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.proyecto.model.Detalle;
import com.sena.proyecto.repository.IDetalleRepository;

@Service
public class DetalleServiceImpl implements IDetalleService {

	@Autowired
	private IDetalleRepository detalleVentaRepository;

	@Override
	public Detalle save(Detalle detalleVenta) {
		return detalleVentaRepository.save(detalleVenta);
	}

}
