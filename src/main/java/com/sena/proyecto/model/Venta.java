package com.sena.proyecto.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ventas")
public class Venta {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaVenta;

    private Integer subTotal;
    
    private Integer valorTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idDetalleFK", nullable = false) 
    private Detalle detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idClienteFK", nullable = false) 
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idEmpleadoFK", nullable = false) 
    private Empleado empleado;


    //Constructores
    
    public Venta() {
    }


    public Venta(Integer idVenta, Date fechaVenta, Integer subTotal, Integer valorTotal, Detalle detalle,
            Cliente cliente, Empleado empleado) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.subTotal = subTotal;
        this.valorTotal = valorTotal;
        this.detalle = detalle;
        this.cliente = cliente;
        this.empleado = empleado;
    }


    public Integer getIdVenta() {
        return idVenta;
    }


    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }


    public Date getFechaVenta() {
        return fechaVenta;
    }


    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }


    public Integer getSubTotal() {
        return subTotal;
    }


    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }


    public Integer getValorTotal() {
        return valorTotal;
    }


    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }


    public Detalle getDetalle() {
        return detalle;
    }


    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }


    public Cliente getCliente() {
        return cliente;
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Empleado getEmpleado() {
        return empleado;
    }


    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }


    
    

}


