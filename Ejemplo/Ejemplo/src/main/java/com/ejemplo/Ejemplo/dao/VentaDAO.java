package com.ejemplo.Ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ejemplo.Ejemplo.model.Venta;

public class VentaDAO {
	
final private Connection con;
	
	public VentaDAO(Connection con) {
		this.con = con;
	}

	public List<Venta> obtenerVentasPorPlanta(int plantaId) {
	    List<Venta> ventas = new ArrayList<>();
	    String sql = "SELECT id, planta_id, cantidad, fecha_venta FROM ventas WHERE planta_id = ?";
	    
	    try (PreparedStatement stm = con.prepareStatement(sql)) {
	        stm.setInt(1, plantaId);
	        ResultSet rs = stm.executeQuery();
	        
	        while (rs.next()) {
	            Venta venta = new Venta();
	            venta.setId(rs.getInt("id"));
	            venta.setPlantaId(rs.getInt("planta_id"));
	            venta.setCantidad(rs.getInt("cantidad"));
	            venta.setFechaVenta(rs.getDate("fecha_venta"));
	            ventas.add(venta);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción de manera apropiada
	    }
	    
	    return ventas;
	}
}
