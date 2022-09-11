package com.sena.proyecto.service;

import java.util.List;

import com.sena.proyecto.model.Empleado;
import com.sena.proyecto.model.IEmpleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{
    @Autowired
    private IEmpleado empleadod;

    @Override
    public List<Empleado> findAll() {
        
        return (List<Empleado>) empleadod.findAll();
    }

    @Override
    public void save(Empleado empleado) {
       empleadod.save(empleado);        
    }

    @Override
    public Empleado findOne(Integer idEmpleado) {
        
        return empleadod.findById(idEmpleado).orElse(null);
    }

    @Override
    public void delete(Integer idEmpleado) {
        empleadod.deleteById(idEmpleado);
        
    }
 
}
