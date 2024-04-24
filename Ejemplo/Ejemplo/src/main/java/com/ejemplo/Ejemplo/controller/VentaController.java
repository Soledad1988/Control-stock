package com.ejemplo.Ejemplo.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.ejemplo.Ejemplo.model.Venta;
import com.ejemplo.Ejemplo.Conexion;
import com.ejemplo.Ejemplo.dao.VentaDAO;

public class VentaController {
	
private VentaDAO ventaDAO;
	
	public VentaController() throws SQLException {
		Connection connection = Conexion.obtenerConexion();
		this.ventaDAO = new VentaDAO(connection);
	}
	
	public List<Venta> obtenerVentasPorPlanta(int plantaId) {
	    return this.ventaDAO.obtenerVentasPorPlanta(plantaId);
	}
	
	public boolean realizarVenta(int codigo, int cantidad) throws SQLException {
	    return this.ventaDAO.realizarVenta(codigo, cantidad);
	}
	
	public int obtenerCantidadVendida(int plantaId) {
	    return this.ventaDAO.obtenerCantidadVendida(plantaId);
	}


	
}
