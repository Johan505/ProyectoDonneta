package com.sena.proyecto.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "domicilios") 
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDomicilio;

    @Column(name="estadoDomicilio", length=20)
    private String estado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idVentaFK", nullable = false) 
    private Venta venta;
    
    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="idDomiciliarioFK", referencedColumnName="idDomiciliario") 
    private Domiciliario domiciliario;

    public Domicilio() {
    }

    public Domicilio(Integer idDomicilio, String estado, Venta venta, Domiciliario domiciliario) {
        this.idDomicilio = idDomicilio;
        this.estado = estado;
        this.venta = venta;
        this.domiciliario = domiciliario;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
