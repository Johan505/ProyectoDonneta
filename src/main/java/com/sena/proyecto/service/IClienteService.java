package com.sena.proyecto.service;

import java.util.List;

import com.sena.proyecto.model.Cliente;

public interface IClienteService {
    public List<Cliente> findAll();

    public void save(Cliente cliente);

    public Cliente findOne(Long idCliente);

    public void delete(Long idCliente);
}
