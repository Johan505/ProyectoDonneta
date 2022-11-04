package com.sena.proyecto.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "domicilios")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDomicilio;

    @NotEmpty
    @Size(min = 3, max = 30)
    private String nombre;

    @NotEmpty
    @Size(min = 15, max = 35)
    @Column(length = 100, nullable = false)
    private String direccion;

    @Column(length = 20, nullable = false, unique = true)
    @Size(min = 7, max = 15)
    private String telefono;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idVentaFK", referencedColumnName = "id")
    private Venta venta;

    @OneToOne
    private Domiciliario domiciliario;

    public Domicilio() {
    }

    public Domicilio(Integer idDomicilio, @NotEmpty @Size(min = 3, max = 30) String nombre,
            @NotEmpty @Size(min = 15, max = 35) String direccion, @Size(min = 7, max = 15) String telefono, Venta venta,
            Domiciliario domiciliario) {
        this.idDomicilio = idDomicilio;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.venta = venta;
        this.domiciliario = domiciliario;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
