package com.sena.proyectodonneta.model;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IProducto extends CrudRepository<Producto, Long> {
    
    @Query ("select p from Producto p where p.estado = true")
    List<Producto> findProductos();
}
