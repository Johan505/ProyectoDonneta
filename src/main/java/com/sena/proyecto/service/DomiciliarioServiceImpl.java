package com.sena.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.proyecto.model.Domiciliario;
import com.sena.proyecto.model.IDomiciliario;

@Service
public class DomiciliarioServiceImpl implements IDomiciliarioService {

    @Autowired
    private IDomiciliario domiciliariod;

    @Override
    public List<Domiciliario> findAll() {

        return (List<Domiciliario>) domiciliariod.findAll();
    }

    @Override
    public void save(Domiciliario domiciliario) {
        domiciliariod.save(domiciliario);
    }

    @Override
    public Domiciliario findOne(Integer idDomiciliario) {
        return domiciliariod.findById(idDomiciliario).orElse(null);
    }

    @Override
    public void delete(Integer idDomiciliario) {
        domiciliariod.deleteById(idDomiciliario);

    }
}
