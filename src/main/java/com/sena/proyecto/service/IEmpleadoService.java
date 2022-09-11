package com.sena.proyecto.service;

import java.util.List;

import com.sena.proyecto.model.Empleado;

public interface IEmpleadoService {
    public List<Empleado> findAll();
    public void save (Empleado empleado);
    public Empleado findOne(Integer idEmpleado);
    public void delete(Integer idEmpleado);
}
