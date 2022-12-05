package com.sena.proyectodonneta.service.impl;

import java.util.List;
import java.util.Optional;

import com.sena.proyectodonneta.model.Domicilio;

public interface IDomicilioService {

	List<Domicilio> findAll();

	Optional<Domicilio> findById(Long idDomicilio);

	Domicilio save(Domicilio domicilio);

	Domicilio findOne(Long idDomicilio);

}
