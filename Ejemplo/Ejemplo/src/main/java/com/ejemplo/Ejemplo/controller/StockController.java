package com.ejemplo.Ejemplo.controller;

import java.sql.Connection;
import java.sql.SQLException;

import com.ejemplo.Ejemplo.Conexion;
import com.ejemplo.Ejemplo.dao.StockDAO;

public class StockController {
	
	private StockDAO stockDAO;
	
	public StockController() throws SQLException {
		Connection connection = Conexion.obtenerConexion();
		this.stockDAO = new StockDAO(connection);
	}
	
	// Método para actualizar el stock en la base de datos
    public void actualizarStock(int codigoPlanta, int nuevoStock) throws SQLException {
        this.stockDAO.actualizarStock(codigoPlanta, nuevoStock);
    }

}
