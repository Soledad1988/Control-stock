package com.ejemplo.Ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReporteVentasDAO {
	
	final private Connection con;
	
	public ReporteVentasDAO(Connection con) {
		this.con = con;
	}
	
	public int obtenerCantidadTotalVendida() throws SQLException {
	    int cantidadTotalVendida = 0;
	    try {
	        String sql = "SELECT SUM(cantidad) AS cantidad_total FROM ventas";
	        PreparedStatement stm = con.prepareStatement(sql);
	        ResultSet rs = stm.executeQuery();
	        if (rs.next()) {
	            cantidadTotalVendida = rs.getInt("cantidad_total");
	        }
	        stm.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción de manera apropiada
	        throw e; // Propagar la excepción para que sea manejada por el código que llama a este método
	    }
	    return cantidadTotalVendida;
	}
	
	 public List<Object[]> obtenerReporteVentas() {
	        List<Object[]> reporteVentas = new ArrayList<>();

	        String sql = "SELECT ventas.id AS venta_id, ventas.planta_id, ventas.cantidad, ventas.fecha_venta, plantas.codigo, plantas.nombrePlanta, plantas.precioVenta " +
	                     "FROM ventas " +
	                     "INNER JOIN plantas ON ventas.planta_id = plantas.id";

	        try (PreparedStatement stm = con.prepareStatement(sql);
	             ResultSet rs = stm.executeQuery()) {
	            
	            while (rs.next()) {
	                int ventaId = rs.getInt("venta_id");
	                int plantaId = rs.getInt("planta_id");
	                int cantidad = rs.getInt("cantidad");
	                Date fechaVenta = rs.getDate("fecha_venta");
	                int codigoPlanta = rs.getInt("codigo");
	                String nombrePlanta = rs.getString("nombrePlanta");
	                double precioVentaUnitario = rs.getDouble("precioVenta");
	                double totalVenta = cantidad * precioVentaUnitario;

	                Object[] ventaInfo = new Object[]{ventaId, plantaId, codigoPlanta, nombrePlanta, cantidad, fechaVenta, precioVentaUnitario, totalVenta};
	                reporteVentas.add(ventaInfo);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Manejar la excepción de manera apropiada
	        }

	        return reporteVentas;
	    }
	




}
