package com.ejemplo.Ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.ejemplo.Ejemplo.model.Planta;

public class PlantaDAO {

	final private Connection con;
	
	public PlantaDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Planta planta) {
		String sql = "INSERT INTO plantas(fechaIngreso, codigo, nombrePlanta, cantidad, precioCosto, precioVenta) VALUES (?,?,?,?,?,?)";

	    try(PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
	    	stm.setDate(1, planta.getFechaIngreso());
	        stm.setInt(2, planta.getCodigo());
	        stm.setString(3, planta.getNombrePlanta());
	        stm.setInt(4, planta.getCantidad());
	        stm.setDouble(5, planta.getPrecioCosto());
	        stm.setDouble(6, planta.getPrecioVenta());
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
	
	 public List<Planta> listar() {
	        List<Planta> plantas = new ArrayList<>();
	        String sql = "SELECT id, fechaIngreso, codigo, nombrePlanta, cantidad, precioCosto, precioVenta FROM plantas";

	        try (PreparedStatement stm = con.prepareStatement(sql)) {
	            try (ResultSet rs = stm.executeQuery()) {
	                while (rs.next()) {
	                    Planta planta = new Planta();
	                    planta.setId(rs.getInt("id"));
	                    planta.setFechaIngreso(rs.getDate("fechaIngreso"));
	                    planta.setCodigo(rs.getInt("codigo"));
	                    planta.setNombrePlanta(rs.getString("nombrePlanta"));
	                    planta.setCantidad(rs.getInt("cantidad"));
	                    planta.setPrecioCosto(rs.getDouble("precioCosto"));
	                    planta.setPrecioVenta(rs.getDouble("precioVenta"));
	                    plantas.add(planta);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }

	        return plantas;
	    }
	
		
		// Método para obtener la cantidad total comprada de una planta específica
	    public int obtenerCantidadTotalComprada(int plantaId) {
	        int cantidadTotalComprada = 0;
	        try {
	            String sql = "SELECT SUM(cantidad) AS cantidad_total FROM plantas WHERE id = ?";
	            PreparedStatement stm = con.prepareStatement(sql);
	            stm.setInt(1, plantaId);
	            ResultSet rs = stm.executeQuery();
	            if (rs.next()) {
	                cantidadTotalComprada = rs.getInt("cantidad_total");
	            }
	            stm.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Manejar la excepción de manera apropiada
	        }
	        return cantidadTotalComprada;
	    }
	    
	    
	    public double calcularPrecioVenta(double precioCosto, double porcentajeGanancia) {
	        double precioVenta = precioCosto * (1 + (porcentajeGanancia / 100));
	        // Redondear el precio de venta a dos decimales
	        DecimalFormat df = new DecimalFormat("#.##");
	        return Double.valueOf(df.format(precioVenta));
	    }
	    
}
