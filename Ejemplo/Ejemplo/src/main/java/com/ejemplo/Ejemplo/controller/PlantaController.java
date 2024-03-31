package com.ejemplo.Ejemplo.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ejemplo.Ejemplo.Conexion;
import com.ejemplo.Ejemplo.dao.PlantaDAO;
import com.ejemplo.Ejemplo.model.Planta;

public class PlantaController {

	private PlantaDAO plantaDAO;
	
	public PlantaController() throws SQLException {
		Connection connection = Conexion.obtenerConexion();
		this.plantaDAO = new PlantaDAO(connection);
	}
	
	public void guardar(Planta planta) {
		this.plantaDAO.guardar(planta);
	}
	
	public double obtenerPrecioVenta(int codigo) {
		return this.plantaDAO.obtenerPrecioVenta(codigo);
	}
	
	public List<Planta> listar() {
		return this.plantaDAO.listar();
	}
	
	public boolean realizarVenta(int codigo, int cantidad) throws SQLException {
	    return this.plantaDAO.realizarVenta(codigo, cantidad);
	}
	
	public int obtenerCantidadVendida(int plantaId) {
	    return this.plantaDAO.obtenerCantidadVendida(plantaId);
	}
}
