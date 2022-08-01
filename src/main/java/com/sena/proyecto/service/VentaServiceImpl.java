package com.sena.proyecto.service;

import java.util.List;

import com.sena.proyecto.model.Venta;
import com.sena.proyecto.model.IVenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaServiceImpl implements IVentaService{
    @Autowired
    private IVenta ventad;

    @Override
    public List<Venta> findAll() {
        
        return (List<Venta>) ventad.findAll();
    }

    @Override
    public void save(Venta venta) {
       ventad.save(venta);        
    }

    @Override
    public Venta findOne(Integer id) {
        
        return ventad.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        ventad.deleteById(id);
        
    }
 
}
