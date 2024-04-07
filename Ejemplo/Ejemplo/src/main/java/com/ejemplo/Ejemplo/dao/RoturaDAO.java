package com.ejemplo.Ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RoturaDAO {
	
final private Connection con;
	
	public RoturaDAO(Connection con) {
		this.con = con;
	}

	/*pasarlo a dao venta*/
    public boolean realizarBajaPorRotura(int codigoPlanta, int cantidadBaja, String detalleRotura) throws SQLException {
        // Actualizar la cantidad en stock
		int nuevaCantidad = obtenerCantidadDisponible(codigoPlanta) - cantidadBaja;
		if (nuevaCantidad < 0) {
		    System.out.println("Error: La cantidad a dar de baja es mayor que la cantidad en stock.");
		    return false;
		}

		actualizarCantidad(codigoPlanta, nuevaCantidad);

		// Obtener la fecha actual para la fecha de baja
		Date fechaBaja = new Date();

		// Actualizar la fecha de baja en la base de datos
		actualizarFechaBaja(codigoPlanta, fechaBaja, detalleRotura);

		System.out.println("Baja por rotura realizada con éxito.");

		return true;
    }
    
    private int obtenerCantidadDisponible(int codigo) {
	    int cantidadDisponible = 0;
	    try {
	        String sql = "SELECT cantidad FROM plantas WHERE codigo = ?";
	        PreparedStatement stm = con.prepareStatement(sql);
	        stm.setInt(1, codigo);
	        ResultSet rs = stm.executeQuery();
	        if (rs.next()) {
	            cantidadDisponible = rs.getInt("cantidad");
	        }
	        stm.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Maneja la excepción de manera apropiada
	    }
	    return cantidadDisponible;
	}
    
    private void actualizarCantidad(int plantaId, int nuevaCantidad) {
	    try {
	        String sql = "UPDATE plantas SET cantidad = ? WHERE id = ?";
	        PreparedStatement stm = con.prepareStatement(sql);
	        stm.setInt(1, nuevaCantidad);
	        stm.setInt(2, plantaId);
	        stm.executeUpdate();
	        stm.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Maneja la excepción de manera apropiada
	    }
	}


    private void actualizarFechaBaja(int plantaId, Date fechaBaja, String detalleRotura) {
        try {
            String sql = "UPDATE ventas SET fecha_baja = ?, descripcion = ? WHERE id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setDate(1, new java.sql.Date(fechaBaja.getTime()));
            stm.setString(2, detalleRotura);
            stm.setInt(3, plantaId);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Maneja la excepción de manera apropiada
        }
    }
    
    private void registrarVentaPorRotura(int plantaId, int cantidad, Date fechaVenta) {
        try {
            String sql = "INSERT INTO ventas (planta_id, cantidad, fecha_venta) VALUES (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, plantaId);
            stm.setInt(2, cantidad);
            stm.setDate(3, new java.sql.Date(fechaVenta.getTime()));
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Maneja la excepción de manera apropiada
        }
    }
}
