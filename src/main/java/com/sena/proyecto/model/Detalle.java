package com.sena.proyecto.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="detalle_venta")
public class Detalle {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalle;

    private Integer cantidadVenta;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idVentaFK", nullable = false) 
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idProductoFK", nullable = false) 
    private Producto producto;

    //Constructores
    public Detalle() {
    }

    public Detalle(Integer idDetalle, Integer cantidadVenta, Venta venta, Producto producto) {
        this.idDetalle = idDetalle;
        this.cantidadVenta = cantidadVenta;
        this.venta = venta;
        this.producto = producto;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(Integer cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    

}