package com.sena.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.proyecto.model.Domicilio;
import com.sena.proyecto.model.IDomicilio;

@Service
public class DomicilioServiceImpl implements IDomicilioService{
    
    @Autowired
    private IDomicilio domiciliod;

    @Override
    public List<Domicilio> findAll() {
        
        return (List<Domicilio>) domiciliod.findAll();
    }

    @Override
    public void save(Domicilio domicilio) {
        domiciliod.save(domicilio);        
    }

    @Override
    public Domicilio findOne(Integer idDomicilio) {
        return domiciliod.findById(idDomicilio).orElse(null);
    }

    @Override
    public void delete(Integer idDomicilio) {
        domiciliod.deleteById(idDomicilio);
        
    }
}
