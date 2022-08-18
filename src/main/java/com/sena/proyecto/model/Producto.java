package com.sena.proyecto.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="productos")
public class Producto {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;
    
    @NotEmpty
    @Size(min=2,max=50)
    private String nombre;

    @NotEmpty
    @Size(min=2,max=200)
    private String descripcion;

    @NotNull
    private Integer precio;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idCategoriaFK", nullable = false) 
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Detalle> detalle;


    //Constructores

    public Producto() {
        detalle=new ArrayList<Detalle>();
    }


    public Producto(Integer idProducto, @NotEmpty @Size(min = 2, max = 50) String nombre,
            @NotEmpty @Size(min = 2, max = 200) String descripcion, @NotNull Integer precio, Categoria categoria,
            List<Detalle> detalle) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.detalle = detalle;
    }


    public Integer getIdProducto() {
        return idProducto;
    }


    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Integer getPrecio() {
        return precio;
    }


    public void setPrecio(Integer precio) {
        this.precio = precio;
    }


    public Categoria getCategoria() {
        return categoria;
    }


    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public List<Detalle> getDetalle() {
        return detalle;
    }


    public void setDetalle(List<Detalle> detalle) {
        this.detalle = detalle;
    }


    
    
}

    