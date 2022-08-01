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

    //Constructores
    public Detalle() {
        
    }

    public Detalle(Integer idDetalle, Integer cantidadVenta, Venta venta) {
        this.idDetalle = idDetalle;
        this.cantidadVenta = cantidadVenta;
        this.venta = venta;
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

    

}


