package com.ejemplo.Ejemplo.model;

import java.util.Date;

public class Rotura {

	  private Integer id;
	  private Integer plantaId;
	  private Integer cantidadBaja;
	  private Date fechaBaja;
	  private String descripcion;
    
    public Rotura() {
	
	}

	public Rotura(Integer id, Integer plantaId, Integer cantidadBaja, Date fechaBaja, String descripcion) {
		super();
		this.id = id;
		this.plantaId = plantaId;
		this.cantidadBaja = cantidadBaja;
		this.fechaBaja = fechaBaja;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlantaId() {
		return plantaId;
	}

	public void setPlantaId(Integer plantaId) {
		this.plantaId = plantaId;
	}

	

	public Integer getCantidadBaja() {
		return cantidadBaja;
	}

	public void setCantidadBaja(Integer cantidadBaja) {
		this.cantidadBaja = cantidadBaja;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    
}
