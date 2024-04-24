package com.ejemplo.Ejemplo.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ejemplo.Ejemplo.Conexion;
import com.ejemplo.Ejemplo.dao.ReporteVentasDAO;

public class ReporteVentasController {
	
	private ReporteVentasDAO reporteDAO;
	
	public ReporteVentasController() throws SQLException {
		Connection connection = Conexion.obtenerConexion();
		this.reporteDAO = new ReporteVentasDAO(connection);
	}

	
	  public int obtenerCantidadTotalVendida() throws SQLException {
	        return reporteDAO.obtenerCantidadTotalVendida();
	    }
	  
	  public List<Object[]> obtenerReporteVentas() {
		  return reporteDAO.obtenerReporteVentas();
	  }
}
