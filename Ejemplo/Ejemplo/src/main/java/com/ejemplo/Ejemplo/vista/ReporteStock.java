package com.ejemplo.Ejemplo.vista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import com.ejemplo.Ejemplo.controller.PlantaController;
import com.ejemplo.Ejemplo.controller.VentaController;
import com.ejemplo.Ejemplo.model.Planta;
import com.ejemplo.Ejemplo.model.Venta;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.*;
import java.sql.SQLException;

public class ReporteStock extends JFrame {
	    private JDateChooser textFechaIngreso;
	    private PlantaController plantaController;
	    private VentaController ventaController;
	    private JTable tabla;
	    private DefaultTableModel modelo;

	    public ReporteStock() throws SQLException {
	        super("Reporte de Stock");

	        this.plantaController = new PlantaController();
	        this.ventaController = new VentaController();

	        // Configuración del diseño del formulario
	        getContentPane().setLayout(null);
	        getContentPane().setBackground(new Color(255, 204, 204)); // Color de fondo

	        // Etiqueta para la fecha
	        JLabel labelFecha = new JLabel("Fecha:");
	        labelFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
	        labelFecha.setBounds(18, 37, 95, 25);
	        getContentPane().add(labelFecha);

	        // Selector de fecha
	        textFechaIngreso = new JDateChooser();
	        textFechaIngreso.setDateFormatString("yyyy-MM-dd");
	        textFechaIngreso.setBounds(143, 37, 128, 30);
	        getContentPane().add(textFechaIngreso);

	        // Botón para generar reporte
	        JButton botonGenerarReporte = new JButton("Generar Reporte");
	        botonGenerarReporte.setBounds(300, 37, 150, 30);
	        getContentPane().add(botonGenerarReporte);

	        // Configurar tabla de contenido
	        configurarTablaDeContenido();

	        // Configuración de la ventana
	        setSize(800, 500);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setVisible(true);

	        // Acción del botón para generar reporte
	        botonGenerarReporte.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
						generarReporte();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	        });
	    }

	    private void configurarTablaDeContenido() {
	        tabla = new JTable();

	        modelo = new DefaultTableModel();
	        modelo.addColumn("Fecha de Ingreso");
	        modelo.addColumn("Fecha de Salida");
	        modelo.addColumn("Código");
	        modelo.addColumn("Nombre");
	        modelo.addColumn("Cantidad Comprada");
	        modelo.addColumn("Cantidad Vendida");
	        modelo.addColumn("Cantidad en Stock");

	        tabla = new JTable(modelo); // Asignar el modelo a la tabla

	        // Ocultar la columna de ID
	        tabla.removeColumn(tabla.getColumnModel().getColumn(0));
	        
	        // Definir el renderizador de celdas personalizado para la columna "Cantidad en Stock"
	        DefaultTableCellRenderer stockRenderer = new DefaultTableCellRenderer() {
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	                // Obtener el valor de la celda en la columna "Cantidad en Stock"
	                int cantidadStock = Integer.parseInt(table.getValueAt(row, column).toString());

	                // Determinar el color de texto según la cantidad en stock
	                Color colorTexto;
	                if (cantidadStock <= 0) {
	                    colorTexto = Color.RED; // Sin stock
	                } else if (cantidadStock <= 10) {
	                    colorTexto = Color.YELLOW; // Menos de 10 en stock
	                } else {
	                    colorTexto = Color.GREEN; // Más de 10 en stock
	                }

	                // Establecer el color de texto
	                comp.setForeground(colorTexto);

	                return comp;
	            }
	        };

	        // Asignar el renderizador de celdas personalizado a la columna "Cantidad en Stock"
	        tabla.getColumnModel().getColumn(5).setCellRenderer(stockRenderer);

	        JScrollPane scrollPane = new JScrollPane(tabla);
	        scrollPane.setBounds(18, 90, 750, 330);
	        getContentPane().add(scrollPane);
	    }
	    
	    private void generarReporte() throws SQLException {
	        // Limpiar tabla antes de generar el reporte
	        limpiarTabla();

	        // Obtener los datos del controlador
	        List<Planta> plantas = plantaController.listar(); // Obtener todas las plantas

	        // Llenar la tabla con los datos obtenidos
	        if (plantas != null) {
	            for (Planta planta : plantas) {
	                // Obtener la cantidad total comprada de la planta
	                int cantidadComprada = plantaController.obtenerCantidadTotalComprada(planta.getId());
	                // Obtener las ventas para esta planta
	                int cantidadVendida = 0;
	                List<Venta> ventas = ventaController.obtenerVentasPorPlanta(planta.getId());
	                for (Venta venta : ventas) {
	                    cantidadVendida += venta.getCantidad();
	                }
	                
	                // Calcular la cantidad en stock
	                int cantidadStock = cantidadComprada - cantidadVendida;

	                // Agregar fila al modelo de la tabla
	                modelo.addRow(new Object[] { planta.getFechaIngreso(), planta.getFechaSalida(), planta.getCodigo(),
	                        planta.getNombrePlanta(), cantidadComprada, cantidadVendida, cantidadStock });
	            }
	        }
	    }





	    private void limpiarTabla() {
	        while (modelo.getRowCount() > 0) {
	            modelo.removeRow(0);
	        }
	    }

	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    ReporteStock frame = new ReporteStock();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	}

