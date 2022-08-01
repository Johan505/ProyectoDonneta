package com.sena.proyecto.service;

import java.util.List;

import com.sena.proyecto.model.Detalle;
import com.sena.proyecto.model.IDetalle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleServiceImpl implements IDetalleService{
    @Autowired
    private IDetalle detalled;

    @Override
    public List<Detalle> findAll() {
        
        return (List<Detalle>) detalled.findAll();
    }

    @Override
    public void save(Detalle detalle) {
       detalled.save(detalle);        
    }

    @Override
    public Detalle findOne(Integer idDetalle) {
        
        return detalled.findById(idDetalle).orElse(null);
    }

    @Override
    public void delete(Integer idDetalle) {
        detalled.deleteById(idDetalle);
        
    }
 
}
