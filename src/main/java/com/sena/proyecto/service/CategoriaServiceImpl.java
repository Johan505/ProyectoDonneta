package com.sena.proyecto.service;

import java.util.List;

import com.sena.proyecto.model.Categoria;
import com.sena.proyecto.model.ICategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
    @Autowired
    private ICategoria categoriad;

    @Override
    public List<Categoria> findAll() {
        
        return (List<Categoria>) categoriad.findAll();
    }

    @Override
    public void save(Categoria categoria) {
       categoriad.save(categoria);        
    }

    @Override
    public Categoria findOne(Integer idCategoria) {
        
        return categoriad.findById(idCategoria).orElse(null);
    }

    @Override
    public void delete(Integer idCategoria) {
        categoriad.deleteById(idCategoria);
        
    }
 
}
