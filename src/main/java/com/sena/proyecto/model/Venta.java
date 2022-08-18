package com.sena.proyecto.model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull
    private Integer subTotal;
    
    @NotNull
    private Integer valorTotal;

    @NotEmpty
    @Size(min=2, max=30)
    private String estado;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idClienteFK", nullable = false) 
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idEmpleadoFK", nullable = false) 
    private Empleado empleado;

    @OneToMany(mappedBy = "venta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Detalle> detalle;

    @OneToOne(mappedBy = "venta") 
    private Domicilio domicilio;


    //Constructores


    public Venta() {
        detalle=new ArrayList<Detalle>();
    }


    public Venta(Integer idVenta, Date fechaVenta, @NotNull Integer subTotal, @NotNull Integer valorTotal,
            @NotEmpty @Size(min = 2, max = 30) String estado, Cliente cliente, Empleado empleado, List<Detalle> detalle,
            Domicilio domicilio) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.subTotal = subTotal;
        this.valorTotal = valorTotal;
        this.estado = estado;
        this.cliente = cliente;
        this.empleado = empleado;
        this.detalle = detalle;
        this.domicilio = domicilio;
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


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
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


    public List<Detalle> getDetalle() {
        return detalle;
    }


    public void setDetalle(List<Detalle> detalle) {
        this.detalle = detalle;
    }


    public Domicilio getDomicilio() {
        return domicilio;
    }


    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }


    

    

}


