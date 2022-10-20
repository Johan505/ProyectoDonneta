package com.sena.proyecto.service;

import java.util.List;

import javax.validation.Valid;

import com.sena.proyecto.model.Categoria;

public interface ICategoriaService {
    public List<Categoria> findAll();

    public void save(@Valid Categoria categoria);

    public Categoria findOne(Integer idCategoria);

    public void delete(Integer idCategoria);
}
