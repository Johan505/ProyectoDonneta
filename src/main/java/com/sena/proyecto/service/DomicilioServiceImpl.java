package com.sena.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.proyecto.model.Domicilio;
import com.sena.proyecto.repository.IDomicilioRepository;

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
