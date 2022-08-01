package com.sena.proyecto.service;

import java.util.List;

import javax.validation.Valid;

import com.sena.proyecto.model.Producto;

public interface IProductoService {
    public List<Producto> findAll();
    public void save (@Valid Producto producto);
    public Producto findOne(Integer idProducto);
    public void delete(Integer idProducto);
}
