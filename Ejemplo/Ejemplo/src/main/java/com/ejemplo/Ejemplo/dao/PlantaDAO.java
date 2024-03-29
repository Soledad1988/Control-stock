package com.ejemplo.Ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ejemplo.Ejemplo.model.Planta;

public class PlantaDAO {

	final private Connection con;
	
	public PlantaDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Planta planta) {
		String sql = "INSERT INTO plantas(fechaIngreso, codigo, nombrePlanta, cantidad, precioCosto, precioVenta, estado) VALUES (?,?,?,?,?,?,?)";

	    try(PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
	    	stm.setDate(1, planta.getFechaIngreso());
	        stm.setInt(2, planta.getCodigo());
	        stm.setString(3, planta.getNombrePlanta());
	        stm.setInt(4, planta.getCantidad());
	        stm.setDouble(5, planta.getPrecioCosto());
	        stm.setDouble(6, planta.getPrecioVenta());
	        stm.setBoolean(7, planta.getEstado());
	        int affectedRows = stm.executeUpdate();
	        
	        System.out.println("Filas afectadas: " + affectedRows); // Imprimir el número de filas afectadas
	        
	        try(ResultSet rst = stm.getGeneratedKeys()){
	            while(rst.next()) {
	                planta.setId(rst.getInt(1));
	            }
	        }
	    } catch(SQLException e) {
	        e.printStackTrace(); // Imprimir el stack trace en caso de error
	        throw new RuntimeException(e);
	    }
	}
	
	public double obtenerPrecioVenta(int codigo) {
        double precioVenta = 0.0;
        try {
            String sql = "SELECT precioVenta FROM plantas WHERE codigo = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, codigo);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                precioVenta = rs.getDouble("precioVenta");
            }
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Maneja la excepción de manera apropiada
        }
        return precioVenta;
    }

}
