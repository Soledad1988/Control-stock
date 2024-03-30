package com.ejemplo.Ejemplo.vista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.ejemplo.Ejemplo.controller.PlantaController;
import com.ejemplo.Ejemplo.model.Planta;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.sql.SQLException;

public class BajaMercaderia extends JFrame {
    private JButton botonBajaVenta, botonBajaPorRotura;
    private JDateChooser textFechaIngreso;
    private PlantaController plantaController;
    private JTable tabla;
    private DefaultTableModel modelo;

    public BajaMercaderia() throws SQLException {
        super("Alta de Mercadería");

        this.plantaController = new PlantaController();
        // Configuración del fondo rosa claro
        getContentPane().setBackground(new Color(255, 204, 204));

        // Configuración del diseño del formulario
        getContentPane().setLayout(null);

        botonBajaVenta = new JButton("Baja por Venta");
        botonBajaVenta.setBounds(126, 324, 128, 25);
        botonBajaVenta.setBackground(Color.GRAY); // Establecer el color de fondo del botón
        botonBajaVenta.setForeground(Color.BLACK);
        
        botonBajaPorRotura = new JButton("Baja por rotura");
        botonBajaPorRotura.setBounds(281, 324, 148, 25);
        botonBajaPorRotura.setBackground(Color.GRAY); // Establecer el color de fondo del botón
        botonBajaPorRotura.setForeground(Color.BLACK);
        getContentPane().add(botonBajaPorRotura);
        


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
    	           // guardar();
    	        }
    	    });

    	 botonBajaPorRotura.addActionListener(new ActionListener() {
    	        public void actionPerformed(ActionEvent e) {
    	            //imprimirEtiqueta();
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

        setSize(675, 397);
        setVisible(true);
        setLocationRelativeTo(null);
    }
 

    private List<Planta> ListarPlantas() {
        return this.plantaController.listar();
    }

    private void cargarTabla() {
        // Llenar Tabla
        List<Planta> plantas = ListarPlantas();
        try {
            for (Planta planta : plantas) {
                modelo.addRow(new Object[] { planta.getId(), planta.getFechaIngreso(), planta.getCodigo(),
                        planta.getNombrePlanta(), planta.getCantidad(), planta.getPrecioCosto(),
                        planta.getPrecioVenta() });
            }
        } catch (Exception e) {
            throw e;
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
