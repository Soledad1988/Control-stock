package com.ejemplo.Ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockDAO {
	
final private Connection con;
	
	public StockDAO(Connection con) {
		this.con = con;
	}

	// Método para actualizar el stock en la base de datos
    public void actualizarStock(int codigoPlanta, int cantidadVendida) {
        try {
            // Obtener el stock actual de la planta
            int stockActual = obtenerStockActual(codigoPlanta);
            
            // Calcular el nuevo stock restando la cantidad vendida del stock actual
            int nuevoStock = stockActual - cantidadVendida;
            
            // Actualizar el stock en la base de datos
            String sql = "UPDATE plantas SET cantidad = ? WHERE codigo = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, nuevoStock);
            stm.setInt(2, codigoPlanta);
            int filasAfectadas = stm.executeUpdate(); // Ejecutar la actualización
            System.out.println("Filas afectadas al actualizar el stock: " + filasAfectadas);
            stm.close(); // Cerrar el PreparedStatement
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el stock en la base de datos.", e); // Lanzar una excepción en caso de error
        }
    }

    // Método para obtener el stock actual de una planta específica
    private int obtenerStockActual(int codigoPlanta) {
        int stockActual = 0;
        try {
            String sql = "SELECT cantidad FROM plantas WHERE codigo = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, codigoPlanta);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                stockActual = rs.getInt("cantidad");
            }
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el stock actual de la planta.", e); // Lanzar una excepción en caso de error
        }
        return stockActual;
    }
    
}
