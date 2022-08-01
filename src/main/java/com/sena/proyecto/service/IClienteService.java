package com.sena.proyecto.service;

import java.util.List;

import com.sena.proyecto.model.Cliente;

public interface IClienteService {
    public List<Cliente> findAll();
    public void save (Cliente cliente);
    public Cliente findOne(Integer idCliente);
    public void delete(Integer idCliente);
}
