package com.sena.proyectodonneta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.proyectodonneta.model.Detalle;
import com.sena.proyectodonneta.repository.IDetalleRepository;

@Service
public class DetalleServiceImpl implements IDetalleService {

	@Autowired
	private IDetalleRepository detalleVentaRepository;

	@Override
	public Detalle save(Detalle detalleVenta) {
		return detalleVentaRepository.save(detalleVenta);
	}

}
