package com.ejemplo.Ejemplo.model;

public class Planta {
	
	private Integer id;
	private Integer codigo;
	private String nombrePlanta;
	private Integer cantidad;
	private Double precio;
	
	public Planta() {
		
	}
	

	public Planta(Integer id, Integer codigo, String nombrePlanta, Integer cantidad, Double precio) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombrePlanta = nombrePlanta;
		this.cantidad = cantidad;
		this.precio = precio;
	}


	public Planta(Integer codigo, String nombrePlanta, Integer cantidad, Double precio) {
		this.codigo = codigo;
		this.nombrePlanta = nombrePlanta;
		this.cantidad = cantidad;
		this.precio = precio;
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	
}
