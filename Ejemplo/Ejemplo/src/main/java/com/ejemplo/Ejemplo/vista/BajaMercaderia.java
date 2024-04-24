package com.ejemplo.Ejemplo.vista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.ejemplo.Ejemplo.controller.PlantaController;
import com.ejemplo.Ejemplo.controller.StockController;
import com.ejemplo.Ejemplo.controller.VentaController;
import com.ejemplo.Ejemplo.model.Planta;
import com.ejemplo.Ejemplo.model.Venta;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.*;
import java.sql.SQLException;

public class BajaMercaderia extends JFrame {
    private JButton botonBajaVenta;
    private JDateChooser textFechaIngreso;
    private PlantaController plantaController;
    private VentaController ventaController;
    private StockController stockController;
    private JTable tabla;
    private JSpinner spinnerCantidad;
    private DefaultTableModel modelo;

    public BajaMercaderia() throws SQLException {
        super("Baja de Mercadería");

        this.plantaController = new PlantaController();
        this.ventaController = new VentaController();
        this.stockController = new StockController();

        // Configuración del fondo rosa claro
        getContentPane().setBackground(new Color(255, 204, 204));

        // Configuración del diseño del formulario
        getContentPane().setLayout(null);

        botonBajaVenta = new JButton("Generar Venta");
        botonBajaVenta.setBounds(224, 324, 128, 25);
        botonBajaVenta.setBackground(Color.GRAY); // Establecer el color de fondo del botón
        botonBajaVenta.setForeground(Color.BLACK);
        
        // Agregar componentes al contenedor
        Container container = getContentPane();
        container.add(botonBajaVenta);
        
        JLabel labelFecha = new JLabel("Fecha:");
        labelFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelFecha.setBounds(18, 37, 95, 25);
        getContentPane().add(labelFecha);
        
        textFechaIngreso = new JDateChooser();
        textFechaIngreso.setDateFormatString("dd-MM-yyyy");
        textFechaIngreso.getCalendarButton().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        textFechaIngreso.getCalendarButton().setBackground(SystemColor.textHighlight);
        textFechaIngreso.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
        textFechaIngreso.setBounds(143, 37, 128, 30);
        textFechaIngreso.getCalendarButton().setBounds(268, 0, 21, 33);
        textFechaIngreso.setBackground(Color.WHITE);
        textFechaIngreso.setBorder(new LineBorder(SystemColor.window));
        textFechaIngreso.setDateFormatString("yyyy-MM-dd");
        textFechaIngreso.setFont(new Font("Roboto", Font.PLAIN, 18));
		//panel.add(textFechaIngreso);
        getContentPane().add(textFechaIngreso);
        

        configurarTablaDeContenido(container);
        
        
        cargarTabla();
        
     // Configuración de la ventana
        setSize(572, 397); // Se ha ajustado el ancho de la ventana
        setLocationRelativeTo(null); // Centrar ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        configurarAccionesDelFormulario();
        
    }
    
    
    
    private void configurarAccionesDelFormulario() {
    	   
    	botonBajaVenta.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	    	realizarBajaVenta();
    	    }
    	});

    	
    }
  
  
    private void configurarTablaDeContenido(Container container) {
        tabla = new JTable();
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Fecha Alta");
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio Costo");
        modelo.addColumn("Precio Venta");
        
        tabla.setModel(modelo);
        
        
     // Ocultar la columna de ID
        tabla.removeColumn(tabla.getColumnModel().getColumn(0));

        tabla.setBounds(10, 307, 760, 219);
        

        container.add(tabla);
        
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(10, 72, 532, 218);
        container.add(scrollPane);
        
        spinnerCantidad = new JSpinner();
        spinnerCantidad.setModel(new SpinnerNumberModel(1, 1, 999, 1)); // Valor inicial, mínimo, máximo, incremento
        spinnerCantidad.setBounds(450, 37, 80, 30);
        container.add(spinnerCantidad);

        setSize(675, 397);
        setVisible(true);
        setLocationRelativeTo(null);
    }
 

    private List<Planta> ListarPlantas() {
        return this.plantaController.listar();
    }

    private void cargarTabla() {
        // Limpiar la tabla
        modelo.setRowCount(0);

        // Llenar la tabla con los datos de las plantas
        List<Planta> plantas = ListarPlantas();
        for (Planta planta : plantas) {
            modelo.addRow(new Object[] { 
            		planta.getId(), 
            		planta.getFechaIngreso(), 
            		planta.getCodigo(),
                    planta.getNombrePlanta(), 
                    planta.getCantidad(), 
                    planta.getPrecioCosto(),
                    planta.getPrecioVenta() });
        }
    }

    // Método para recargar los datos de la tabla
    private void recargarTabla() {
        modelo.setRowCount(0); // Limpiar la tabla
        cargarTabla(); // Cargar los datos nuevamente
    }
    
    
    // Método para realizar la baja por venta
    private void realizarBajaVenta() {
        try {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione una planta de la tabla para realizar la venta.");
            } else {
                int codigoPlanta = (int) modelo.getValueAt(filaSeleccionada, 2); // Obtener el código de la planta
                int cantidadVendida = (int) spinnerCantidad.getValue(); // Obtener la cantidad vendida del JSpinner

                // Obtener la cantidad actual de la planta en stock
                int cantidadActual = (int) modelo.getValueAt(filaSeleccionada, 4);

                if (cantidadVendida > cantidadActual) {
                    JOptionPane.showMessageDialog(null, "No hay suficiente cantidad en stock para realizar la venta.");
                    return;
                }

                // Realizar la venta y obtener si fue exitosa
                boolean ventaRealizada = ventaController.realizarVenta(codigoPlanta, cantidadVendida);
                if (ventaRealizada) {
                    JOptionPane.showMessageDialog(null, "Venta realizada con éxito.");

                    // Calcular la nueva cantidad
                    int nuevaCantidad = cantidadActual - cantidadVendida;

                    // Actualizar la cantidad en el modelo de la tabla
                    modelo.setValueAt(nuevaCantidad, filaSeleccionada, 4);

                    // Actualizar la tabla de ventas
                    recargarTablaVentas(codigoPlanta);

                    // Actualizar el valor del spinner con la nueva cantidad
                    spinnerCantidad.setValue(nuevaCantidad);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al realizar la venta: " + ex.getMessage());
        }
    }


 // Método para recargar los datos de la tabla de ventas
    private void recargarTablaVentas(int codigoPlanta) {
        // Limpiar la tabla de ventas
        modelo.setRowCount(0);

        // Recargar la tabla de ventas con los datos actualizados
        List<Venta> ventas = ventaController.obtenerVentasPorPlanta(codigoPlanta);
        for (Venta venta : ventas) {
            Object[] fila = {venta.getId(), venta.getPlantaId(), venta.getCantidad(), venta.getFechaVenta()};
            modelo.addRow(fila);
        }
    }
    
    
    



    public static void main(String[] args) {
 		EventQueue.invokeLater(new Runnable() {
 			public void run() {
 				try {
 					BajaMercaderia frame = new BajaMercaderia();
 					frame.setVisible(true);
 				} catch (Exception e) {
 					e.printStackTrace();
 				}
 			}
 		});
 	}
}

