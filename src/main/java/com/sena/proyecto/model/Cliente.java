package com.sena.proyecto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente {
    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String foto;

    @NotEmpty
    @Column(length = 50, nullable = false)
    public String tipodoc;

    @NotEmpty
    @Size(min = 8, max = 10)
    @Column(length = 50, nullable = false, unique = true)
    private String noDocumento;

    @NotEmpty
    @Column(name = "nombre", length = 40)
    @Size(min = 3, max = 30)
    private String nombre;

    @NotEmpty
    @Column(name = "apellido", length = 40)
    @Size(min = 3, max = 30)
    private String apellido;

    @NotEmpty
    @Email
    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @NotEmpty
    @Column(length = 20, nullable = false, unique = true)
    @Size(min = 7, max = 15)
    private String telefono;

    @NotEmpty
    @Size(min = 15, max = 35)
    @Column(length = 100, nullable = false)
    private String direccion;

    @Column
    private boolean enabled;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Venta> venta;

    // Constructores
    public Cliente() {
        venta = new ArrayList<Venta>();
    }

    public Cliente(Long idCliente, String foto, @NotEmpty String tipodoc,
            @NotEmpty @Size(min = 8, max = 10) String noDocumento, @NotEmpty @Size(min = 3, max = 30) String nombre,
            @NotEmpty @Size(min = 3, max = 30) String apellido, @NotEmpty @Email String email,
            @NotEmpty @Size(min = 7, max = 15) String telefono, @NotEmpty @Size(min = 15, max = 35) String direccion,
            boolean enabled, List<Venta> venta) {
        this.idCliente = idCliente;
        this.foto = foto;
        this.tipodoc = tipodoc;
        this.noDocumento = noDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.enabled = enabled;
        this.venta = venta;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Venta> getVenta() {
        return venta;
    }

    public void setVenta(List<Venta> venta) {
        this.venta = venta;
    }

}
