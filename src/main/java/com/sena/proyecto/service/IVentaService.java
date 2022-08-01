package com.sena.proyecto.service;

import java.util.List;

import javax.validation.Valid;

import com.sena.proyecto.model.Venta;

public interface IVentaService {
    public List<Venta> findAll();
    public void save (@Valid Venta venta);
    public Venta findOne(Integer idVenta);
    public void delete(Integer idVenta);
}
