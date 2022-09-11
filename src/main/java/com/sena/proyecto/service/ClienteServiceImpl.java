package com.sena.proyecto.service;

import java.util.List;

import com.sena.proyecto.model.Cliente;
import com.sena.proyecto.model.ICliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService{
    @Autowired
    private ICliente cliented;

    @Override
    public List<Cliente> findAll() {
        
        return (List<Cliente>) cliented.findAll();
    }

    @Override
    public void save(Cliente cliente) {
       cliented.save(cliente);        
    }

    @Override
    public Cliente findOne(Integer idCliente) {
        
        return cliented.findById(idCliente).orElse(null);
    }

    @Override
    public void delete(Integer idCliente) {
        cliented.deleteById(idCliente);
        
    }
 
}
