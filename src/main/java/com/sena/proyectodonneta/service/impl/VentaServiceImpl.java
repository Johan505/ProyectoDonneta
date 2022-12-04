package com.sena.proyectodonneta.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.proyectodonneta.model.Venta;
import com.sena.proyectodonneta.repository.IVentaRepository;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaRepository ventaRepository;

	@Override
	public Venta save(Venta venta) {
		return ventaRepository.save(venta);
	}

	@Override
	public List<Venta> findAll() {
		return ventaRepository.findAll();
	}

	// 0000010
	public String generarNumeroVenta() {
		long numero = 0;
		String numeroConcatenado = "";

		List<Venta> ventas = findAll();

		List<Long> numeros = new ArrayList<Long>();

		ventas.stream().forEach(o -> numeros.add(Long.parseLong(o.getNumero())));

		if (ventas.isEmpty()) {
			numero = 1;
		} else {
			numero = numeros.stream().max(Long::compare).get();
			numero++;
		}

		if (numero < 10) { // 0000001000
			numeroConcatenado = "000000000" + String.valueOf(numero);
		} else if (numero < 100) {
			numeroConcatenado = "00000000" + String.valueOf(numero);
		} else if (numero < 1000) {
			numeroConcatenado = "0000000" + String.valueOf(numero);
		} else if (numero < 10000) {
			numeroConcatenado = "0000000" + String.valueOf(numero);
		}

		return numeroConcatenado;
	}

	@Override
	public Optional<Venta> findById(Long id) {
		return ventaRepository.findById(id);
	}

}
