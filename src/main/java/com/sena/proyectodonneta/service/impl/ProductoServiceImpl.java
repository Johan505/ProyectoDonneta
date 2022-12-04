package com.sena.proyectodonneta.service.impl;

import java.util.List;
import java.util.Optional;

import com.sena.proyectodonneta.model.Producto;
import com.sena.proyectodonneta.model.IProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements IProductoService {
    @Autowired
    private IProducto productod;

    @Autowired
    private IProducto productoda;

    @Override
    public List<Producto> findProductos() {

        return (List<Producto>) productod.findProductos();
    }

    @Override
    public List<Producto> findAll() {

    return (List<Producto>) productoda.findAll();
    }


    @Override
    public void save(Producto producto) {
        productod.save(producto);
    }

    @Override
    public Optional<Producto> get(Long idProducto) {
        return productod.findById(idProducto);
    }

    @Override
    public Producto findOne(Long idProducto) {

        return productod.findById(idProducto).orElse(null);
    }

    @Override
    public void delete(Long idProducto) {
        productod.deleteById(idProducto);

    }

}
