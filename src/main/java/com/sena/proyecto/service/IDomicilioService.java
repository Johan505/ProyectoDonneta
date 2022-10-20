package com.sena.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.sena.proyecto.model.Domicilio;

public interface IDomicilioService {

	List<Domicilio> findAll();

	Optional<Domicilio> findById(Integer idDomicilio);

	Domicilio save(Domicilio domicilio);

}
