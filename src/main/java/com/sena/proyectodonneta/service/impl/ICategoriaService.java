package com.sena.proyectodonneta.service.impl;

import java.util.List;

import javax.validation.Valid;

import com.sena.proyectodonneta.model.Categoria;

public interface ICategoriaService {
    public List<Categoria> findAll();

    public void save(@Valid Categoria categoria);

    public Categoria findOne(Long idCategoria);

    public void delete(Long idCategoria);
}
