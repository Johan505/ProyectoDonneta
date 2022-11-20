package com.sena.proyectodonneta.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.proyectodonneta.model.Domicilio;
import com.sena.proyectodonneta.repository.IDomicilioRepository;

@Service
public class DomicilioServiceImpl implements IDomicilioService {

	@Autowired
	private IDomicilioRepository domicilioRepository;

	@Override
	public Domicilio save(Domicilio domicilio) {
		return domicilioRepository.save(domicilio);
	}

	@Override
	public List<Domicilio> findAll() {
		return domicilioRepository.findAll();
	}

	@Override
	public Optional<Domicilio> findById(Integer idDomicilio) {
		return domicilioRepository.findById(idDomicilio);
	}
}
