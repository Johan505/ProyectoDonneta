package com.sena.proyecto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "domiciliarios") 
public class Domiciliario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDomiciliario;

    private String foto;

    @NotEmpty
    @Column(length = 50,nullable=false)
    public String tipodoc;

    @NotEmpty
    @Column(name="noDocumento", length=20)
    private String noDocumento;

    @NotEmpty
    @Column(name="nombre", length=40)
    private String nombre;

    @NotEmpty
    @Column(name="apellido", length=40)
    private String apellido;

    @NotEmpty
    @Column(name="telefono", length=20)
    private String telefono;

    @NotEmpty
    @Column(name="tipoTransporte", length=20)
    private String tipoTransporte;

    @Column(name="estado")
    private Boolean estado;

    @OneToOne(mappedBy = "domiciliario") 
    private Domicilio domicilio;


    public Domiciliario() {
    }


    public Domiciliario(Integer idDomiciliario, String foto, @NotEmpty String tipodoc, @NotEmpty String noDocumento,
            @NotEmpty String nombre, @NotEmpty String apellido, @NotEmpty String telefono,
            @NotEmpty String tipoTransporte, Boolean estado, Domicilio domicilio) {
        this.idDomiciliario = idDomiciliario;
        this.foto = foto;
        this.tipodoc = tipodoc;
        this.noDocumento = noDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.tipoTransporte = tipoTransporte;
        this.estado = estado;
        this.domicilio = domicilio;
    }


    public Integer getIdDomiciliario() {
        return idDomiciliario;
    }


    public void setIdDomiciliario(Integer idDomiciliario) {
        this.idDomiciliario = idDomiciliario;
    }


    public String getFoto() {
        return foto;
    }


    public void setFoto(String foto) {
        this.foto = foto;
    }


    public String getTipodoc() {
        return tipodoc;
    }


    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }


    public String getNoDocumento() {
        return noDocumento;
    }


    public void setNoDocumento(String noDocumento) {
        this.noDocumento = noDocumento;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellido() {
        return apellido;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getTipoTransporte() {
        return tipoTransporte;
    }


    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }


    public Boolean getEstado() {
        return estado;
    }


    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


    public Domicilio getDomicilio() {
        return domicilio;
    }


    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    
    
}