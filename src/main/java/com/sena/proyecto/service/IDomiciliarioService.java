package com.sena.proyecto.service;

import java.util.List;

import com.sena.proyecto.model.Domiciliario;

public interface IDomiciliarioService {
    public List<Domiciliario> findAll();

    public void save(Domiciliario domiciliario);

    public Domiciliario findOne(Integer idDomiciliario);

    public void delete(Integer idDomiciliario);
}
