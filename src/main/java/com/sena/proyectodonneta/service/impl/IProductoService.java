package com.sena.proyectodonneta.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.sena.proyectodonneta.model.Producto;

public interface IProductoService {

    public List<Producto> findProductos();

    public List<Producto> findAll();

    public void save(@Valid Producto producto);

    public Optional<Producto> get(Long idProducto);

    public Producto findOne(Long idProducto);

    public void delete(Long idProducto);
}
