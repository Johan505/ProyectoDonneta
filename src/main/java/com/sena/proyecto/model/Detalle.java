package com.sena.proyecto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="detalle_venta")
public class Detalle {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalle;

    private Integer cantidadVenta;


    @OneToMany(mappedBy = "detalle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Venta> venta;

    //Constructores
    public Detalle() {
        venta=new ArrayList<Venta>();
    }

    public Detalle(Integer idDetalle, Integer cantidadVenta, List<Venta> venta) {
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

    public List<Venta> getVenta() {
        return venta;
    }

    public void setVenta(List<Venta> venta) {
        this.venta = venta;
    }

}