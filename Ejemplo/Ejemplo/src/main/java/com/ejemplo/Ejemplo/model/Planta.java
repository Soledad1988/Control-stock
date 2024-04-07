package com.ejemplo.Ejemplo.model;

import java.sql.Date;

public class Planta {
	
	private Integer id;
	private Date fechaIngreso;
	private Date fechaSalida;
	private Integer codigo;
	private String nombrePlanta;
	private Integer cantidad;
	private Double precioCosto;
	private Double precioVenta;
	
	
	public Planta() {
		
	}


	public Planta(Integer id, Date fechaIngreso, Date fechaSalida, Integer codigo, String nombrePlanta,
			Integer cantidad, Double precioCosto, Double precioVenta) {
		super();
		this.id = id;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.codigo = codigo;
		this.nombrePlanta = nombrePlanta;
		this.cantidad = cantidad;
		this.precioCosto = precioCosto;
		this.precioVenta = precioVenta;
	}


	public Planta(Date fechaIngreso, Integer codigo, String nombrePlanta, Integer cantidad, 
			Double precioCosto,Double precioVenta) {
		this.fechaIngreso = fechaIngreso;
		this.codigo = codigo;
		this.nombrePlanta = nombrePlanta;
		this.cantidad = cantidad;
		this.precioCosto = precioCosto;
		this.precioVenta = precioVenta;
	}
	
	public Double getPrecioCosto() {
		return precioCosto;
	}


	public void setPrecioCosto(Double precioCosto) {
		this.precioCosto = precioCosto;
	}


	public Double getPrecioVenta() {
		return precioVenta;
	}


	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}


	public Date getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public Date getFechaSalida() {
		return fechaSalida;
	}


	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombrePlanta() {
		return nombrePlanta;
	}

	public void setNombrePlanta(String nombrePlanta) {
		this.nombrePlanta = nombrePlanta;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	//---------------------------------------
	public void calcularPrecioVenta(double porcentajeGanancia) {
	    this.precioVenta = this.precioCosto * (1 + porcentajeGanancia / 100);
	}
	
}
