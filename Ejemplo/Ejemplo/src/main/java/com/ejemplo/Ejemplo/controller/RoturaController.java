package com.ejemplo.Ejemplo.controller;

import java.sql.Connection;
import java.sql.SQLException;

import com.ejemplo.Ejemplo.Conexion;
import com.ejemplo.Ejemplo.dao.RoturaDAO;

public class RoturaController {
	
	private RoturaDAO roturaDAO;
	
	public RoturaController() throws SQLException {
		Connection connection = Conexion.obtenerConexion();
		this.roturaDAO = new RoturaDAO(connection);
	}

	 public boolean realizarBajaPorRotura(int codigoPlanta, int cantidadBaja, String detalleRotura) throws SQLException {
	    	return this.roturaDAO.realizarBajaPorRotura(codigoPlanta, cantidadBaja, detalleRotura);
	    }
}
