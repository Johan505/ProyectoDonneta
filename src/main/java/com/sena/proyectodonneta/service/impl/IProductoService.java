package com.sena.proyectodonneta.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.sena.proyectodonneta.model.Producto;

public interface IProductoService {

    public List<Producto> findProductos();

    public List<Producto> findAll();
    public List<Producto> findAlla();

    public void save(@Valid Producto producto);

    public Optional<Producto> get(Integer idProducto);

    public Producto findOne(Integer idProducto);

    public void delete(Integer idProducto);
}
