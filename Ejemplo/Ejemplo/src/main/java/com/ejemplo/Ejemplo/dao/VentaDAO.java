package com.ejemplo.Ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ejemplo.Ejemplo.model.Planta;
import com.ejemplo.Ejemplo.model.Venta;

public class VentaDAO {
	
final private Connection con;
	
	public VentaDAO(Connection con) {
		this.con = con;
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
	    if (nuevaCantidad < 0) {
	        System.out.println("Error: La cantidad vendida es mayor que la cantidad en stock.");
	        return false;
	    }

	    actualizarCantidad(planta.getId(), nuevaCantidad);

	    // Obtener la fecha actual para la fecha de venta
	    Date fechaVenta = new Date(); // Obtener la fecha actual
	    
	    // Actualizar la fecha de salida en la base de datos
	    actualizarFechaVenta(planta.getId(), fechaVenta);

	    // Registrar la venta en la tabla ventas
	    registrarVenta(planta.getId(), cantidad, fechaVenta); // Pasar la fechaVenta

	    System.out.println("Venta realizada con éxito.");

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
		            planta.setCodigo(rs.getInt("codigo"));
		            planta.setNombrePlanta(rs.getString("nombrePlanta"));
		            planta.setCantidad(rs.getInt("cantidad"));
		            planta.setPrecioCosto(rs.getDouble("precioCosto"));
		            planta.setPrecioVenta(rs.getDouble("precioVenta"));
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

		 private void actualizarFechaVenta(int ventaId, Date fechaVenta) {
			    try {
			        String sql = "UPDATE ventas SET fecha_venta = ? WHERE id = ?";
			        PreparedStatement stm = con.prepareStatement(sql);
			        stm.setDate(1, new java.sql.Date(fechaVenta.getTime()));
			        stm.setInt(2, ventaId);
			        stm.executeUpdate();
			        stm.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			        // Maneja la excepción de manera apropiada
			    }
			}

		

		private void registrarVenta(int plantaId, int cantidad, Date fechaVenta) {
		    try {
		        String sql = "INSERT INTO ventas (planta_id, cantidad, fecha_venta) VALUES (?, ?, ?)";
		        PreparedStatement stm = con.prepareStatement(sql);
		        stm.setInt(1, plantaId);
		        stm.setInt(2, cantidad);
		        stm.setDate(3, new java.sql.Date(fechaVenta.getTime())); // Establecer la fechaVenta
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
