package com.sena.proyecto.service;

import java.util.List;

import javax.validation.Valid;

import com.sena.proyecto.model.Detalle;

public interface IDetalleService {
    public List<Detalle> findAll();
    public void save (@Valid Detalle detalle);
    public Detalle findOne(Integer idDetalle);
    public void delete(Integer idDetalle);
}
