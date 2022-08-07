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
    private String marca;
    
    @NotEmpty
    @Size(min=2,max=50)
    private String nombre;

    private Integer cantidad;

    
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


    public Producto(Integer idProducto, @NotEmpty @Size(min = 2, max = 50) String marca,
            @NotEmpty @Size(min = 2, max = 50) String nombre, @NotEmpty Integer cantidad, @NotEmpty Integer precio,
            Categoria categoria) {
        this.idProducto = idProducto;
        this.marca = marca;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categoria = categoria;
    }


    public Integer getIdProducto() {
        return idProducto;
    }


    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }


    public String getMarca() {
        return marca;
    }


    public void setMarca(String marca) {
        this.marca = marca;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Integer getCantidad() {
        return cantidad;
    }


    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
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


    
    
    
}

    