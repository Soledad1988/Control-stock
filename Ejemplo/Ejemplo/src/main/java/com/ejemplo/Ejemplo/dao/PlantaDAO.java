package com.ejemplo.Ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	 
	 
	 public boolean realizarVenta(int codigo, int cantidad) throws SQLException {
		    int cantidadDisponible = obtenerCantidadDisponible(codigo);
		    if (cantidadDisponible < cantidad) {
		        System.out.println("No hay suficiente cantidad en stock para realizar la venta.");
		        return false;
		    }

		    // Obtener la planta correspondiente al código
		    Planta planta = obtenerPlantaPorCodigo(codigo);

		    // Actualizar la cantidad en stock
		    int nuevaCantidad = planta.getCantidad() - cantidad;
		    actualizarCantidad(planta.getId(), nuevaCantidad);

		    // Obtener la fecha actual para la fecha de salida
		    Date fechaVenta = new Date();

		    // Actualizar la fecha de salida en la base de datos
		    actualizarFechaSalida(planta.getId(), fechaVenta);

		    // Registrar la venta en la tabla ventas
		    registrarVenta(planta.getId(), cantidad, fechaVenta);

		    System.out.println("Venta realizada con éxito.");

		    return true;
		}

		private void registrarVenta(int plantaId, int cantidad, Date fechaVenta) {
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

		private Planta obtenerPlantaPorCodigo(int codigo) {
		    Planta planta = null;
		    try {
		        String sql = "SELECT * FROM plantas WHERE codigo = ?";
		        PreparedStatement stm = con.prepareStatement(sql);
		        stm.setInt(1, codigo);
		        ResultSet rs = stm.executeQuery();
		        if (rs.next()) {
		            planta = new Planta();
		            planta.setId(rs.getInt("id"));
		            planta.setFechaIngreso(rs.getDate("fechaIngreso"));
		            planta.setFechaSalida(rs.getDate("fechaSalida"));
		            planta.setCodigo(rs.getInt("codigo"));
		            planta.setNombrePlanta(rs.getString("nombrePlanta"));
		            planta.setCantidad(rs.getInt("cantidad"));
		            planta.setPrecioCosto(rs.getDouble("precioCosto"));
		            planta.setPrecioVenta(rs.getDouble("precioVenta"));
		            planta.setEstado(rs.getBoolean("estado"));
		        }
		        stm.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        // Maneja la excepción de manera apropiada
		    }
		    return planta;
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

		private void actualizarFechaSalida(int plantaId, Date fechaSalida) {
		    try {
		        String sql = "UPDATE plantas SET fechaSalida = ? WHERE id = ?";
		        PreparedStatement stm = con.prepareStatement(sql);
		        stm.setDate(1, new java.sql.Date(fechaSalida.getTime()));
		        stm.setInt(2, plantaId);
		        stm.executeUpdate();
		        stm.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        // Maneja la excepción de manera apropiada
		    }
		}
		
		public int obtenerCantidadVendida(int plantaId) {
		    int cantidadVendida = 0;
		    try {
		        String sql = "SELECT SUM(cantidad) AS cantidad_total FROM ventas WHERE planta_id = ?";
		        PreparedStatement stm = con.prepareStatement(sql);
		        stm.setInt(1, plantaId);
		        ResultSet rs = stm.executeQuery();
		        if (rs.next()) {
		            cantidadVendida = rs.getInt("cantidad_total");
		        }
		        stm.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        // Maneja la excepción de manera apropiada
		    }
		    return cantidadVendida;
		}

		
		

}
