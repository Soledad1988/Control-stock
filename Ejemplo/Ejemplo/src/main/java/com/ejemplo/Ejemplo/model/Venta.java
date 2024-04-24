package com.ejemplo.Ejemplo.model;

import java.util.Date;

public class Venta {

	private Integer id;
    private Integer plantaId;
    private Integer cantidad;
    private Date fechaVenta;

    public Venta() {
    	
    }

    public Venta(Integer id, Integer plantaId, Integer cantidad, Date fechaVenta) {
		super();
		this.id = id;
		this.plantaId = plantaId;
		this.cantidad = cantidad;
		this.fechaVenta = fechaVenta;
	}

	// Getters y Setters

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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
