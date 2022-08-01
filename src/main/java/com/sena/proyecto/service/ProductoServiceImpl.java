package com.sena.proyecto.service;

import java.util.List;

import com.sena.proyecto.model.Producto;
import com.sena.proyecto.model.IProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements IProductoService{
    @Autowired
    private IProducto productod;

    @Override
    public List<Producto> findAll() {
        
        return (List<Producto>) productod.findAll();
    }

    @Override
    public void save(Producto producto) {
       productod.save(producto);        
    }

    @Override
    public Producto findOne(Integer idProducto) {
        
        return productod.findById(idProducto).orElse(null);
    }

    @Override
    public void delete(Integer idProducto) {
        productod.deleteById(idProducto);
        
    }
 
}
