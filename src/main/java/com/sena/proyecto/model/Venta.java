package com.sena.proyecto.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ventas")
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numero;
	private Date fechaCreacion;
	private Date fechaRecibida;

	private double total;
	
	
	@OneToMany(mappedBy = "venta")
	private List<Detalle> detalle;
	
	public Venta() {
	
	}

	

	public Venta(Integer id, String numero, Date fechaCreacion, Date fechaRecibida, double total) {
		super();
		this.id = id;
		this.numero = numero;
		this.fechaCreacion = fechaCreacion;
		this.fechaRecibida = fechaRecibida;
		this.total = total;
	}


	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}



	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public Date getFechaRecibida() {
		return fechaRecibida;
	}



	public void setFechaRecibida(Date fechaRecibida) {
		this.fechaRecibida = fechaRecibida;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public List<Detalle> getDetalle() {
		return detalle;
	}



	public void setDetalle(List<Detalle> detalle) {
		this.detalle = detalle;
	}



	@Override
	public String toString() {
		return "Venta [id=" + id + ", numero=" + numero + ", fechaCreacion=" + fechaCreacion + ", fechaRecibida="
				+ fechaRecibida + ", total=" + total + "]";
	}
	

}
