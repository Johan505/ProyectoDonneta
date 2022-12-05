package com.sena.proyectodonneta.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// Implementa los getter
@Getter

// Implementa los setter
@Setter

// Implementa un contructor con parametros y uno sin
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "domicilios")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDomicilio;

    private String nombre;

    private String direccion;

    private String barrio;

    private String telefono;

    private String estado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idVentaFK", referencedColumnName = "id")
    private Venta venta;

    
    @ManyToOne
    private User user;

}