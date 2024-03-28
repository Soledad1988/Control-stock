package com.ejemplo.Ejemplo.controller;

import java.sql.Connection;
import java.sql.SQLException;

import com.ejemplo.Ejemplo.Conexion;
import com.ejemplo.Ejemplo.dao.PlantaDAO;
import com.ejemplo.Ejemplo.model.Planta;

public class PlantaController {

	private PlantaDAO plantaDAO;
	
	public PlantaController() throws SQLException {
		Connection connection = Conexion.obtenerConexion();
		this.plantaDAO = new PlantaDAO(connection);
	}
	
	public void guaradr(Planta planta) {
		this.plantaDAO.guardar(planta);
	}
}