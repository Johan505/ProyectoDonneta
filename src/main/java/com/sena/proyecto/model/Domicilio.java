package com.sena.proyecto.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "domicilios") 
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDomicilio;

    @NotNull
    private Integer trayecto;

    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="idVentaFK", referencedColumnName="idVenta") 
    private Venta venta;
    
    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="idDomiciliarioFK", referencedColumnName="idDomiciliario") 
    private Domiciliario domiciliario;

    public Domicilio() {
    }

    public Domicilio(Integer idDomicilio, @NotNull Integer trayecto, Venta venta, Domiciliario domiciliario) {
        this.idDomicilio = idDomicilio;
        this.trayecto = trayecto;
        this.venta = venta;
        this.domiciliario = domiciliario;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public Integer getTrayecto() {
        return trayecto;
    }

    public void setTrayecto(Integer trayecto) {
        this.trayecto = trayecto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Domiciliario getDomiciliario() {
        return domiciliario;
    }

    public void setDomiciliario(Domiciliario domiciliario) {
        this.domiciliario = domiciliario;
    }

    
    
    
}
