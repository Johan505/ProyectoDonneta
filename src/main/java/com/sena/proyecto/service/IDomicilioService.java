package com.sena.proyecto.service;

import java.util.List;

import com.sena.proyecto.model.Domicilio;

public interface IDomicilioService {
    public List<Domicilio> findAll();
    public void save (Domicilio domicilio);
    public Domicilio findOne(Integer idDomicilio);
    public void delete(Integer idDomicilio);
}
