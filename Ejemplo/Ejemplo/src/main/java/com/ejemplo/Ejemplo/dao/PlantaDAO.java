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
	    String sql = "INSERT INTO plantas(codigo, nombrePlanta, cantidad, precio) VALUES (?,?,?,?)";

	    try(PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
	        stm.setInt(1, planta.getCodigo());
	        stm.setString(2, planta.getNombrePlanta());
	        stm.setInt(3, planta.getCantidad());
	        stm.setDouble(4, planta.getPrecio());
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

}
