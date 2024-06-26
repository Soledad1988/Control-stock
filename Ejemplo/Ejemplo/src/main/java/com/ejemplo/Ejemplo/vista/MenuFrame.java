package com.ejemplo.Ejemplo.vista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.ejemplo.Ejemplo.controller.PlantaController;


import java.awt.*;
import java.sql.SQLException;

public class MenuFrame extends JFrame {
	private AltaMercaderia ingresoMercaderias;
	private BajaMercaderia bajaMercaderias;
	private ReporteStock reporte;
	private ReporteVentas reporteVentas;
    private JButton botonIngreso, botonEgreso, botonReporte, botonReporteVentas;
    private PlantaController plantaController;

    public MenuFrame() throws SQLException {
        super("Alta de Mercadería");

        this.plantaController = new PlantaController();
        // Configuración del fondo rosa claro
        getContentPane().setBackground(new Color(255, 204, 204));

        // Configuración del diseño del formulario
        getContentPane().setLayout(null);

        botonIngreso = new JButton("Ingreso");
        botonIngreso.setFont(new Font("Tahoma", Font.BOLD, 14));
        botonIngreso.setBounds(10, 69, 125, 67);
        botonIngreso.setBackground(Color.GRAY); // Establecer el color de fondo del botón
        botonIngreso.setForeground(Color.BLACK); // Establecer el color del texto del botón
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\brent\\Downloads\\planta.jpg"));
        lblNewLabel.setBounds(160, 69, 169, 185);
        getContentPane().add(lblNewLabel);
        
        // Agregar componentes al contenedor
        Container container = getContentPane();
        container.add(botonIngreso);
        
        botonEgreso = new JButton("Egreso");
        botonEgreso.setFont(new Font("Tahoma", Font.BOLD, 14));
        botonEgreso.setForeground(Color.BLACK);
        botonEgreso.setBackground(Color.GRAY);
        botonEgreso.setBounds(10, 161, 125, 67);
        getContentPane().add(botonEgreso);
        
        botonReporte = new JButton("Reporte Stock");
        botonReporte.setFont(new Font("Tahoma", Font.BOLD, 14));
        botonReporte.setForeground(Color.BLACK);
        botonReporte.setBackground(Color.GRAY);
        botonReporte.setBounds(339, 69, 146, 67);
        getContentPane().add(botonReporte);
        
        botonReporteVentas = new JButton("Reporte Ventas");
        botonReporteVentas.setFont(new Font("Tahoma", Font.BOLD, 14));
        botonReporteVentas.setForeground(SystemColor.desktop);
        botonReporteVentas.setBackground(Color.GRAY);
        botonReporteVentas.setBounds(339, 161, 146, 67);
        getContentPane().add(botonReporteVentas);
        
        JLabel lblTituloMenu = new JLabel("Menú");
        lblTituloMenu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        lblTituloMenu.setBounds(226, 11, 82, 25);
        getContentPane().add(lblTituloMenu);
        

     // Configuración de la ventana
        setSize(532, 351); // Se ha ajustado el ancho de la ventana
        setLocationRelativeTo(null); // Centrar ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        configurarAccionesDelFormulario();
        
    }
    
    
    
    private void configurarAccionesDelFormulario() {
    	   
    	 botonIngreso.addActionListener(new ActionListener() {
    	        public void actionPerformed(ActionEvent e) {
    	        	try {
						abrirAltaMercaderias();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    	        }
    	    });
    	 
    	 botonEgreso.addActionListener(new ActionListener() {
 	        public void actionPerformed(ActionEvent e) {
 	        	try {
						abrirBajaMercaderias();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
 	        }
 	    });
    	 
    	 botonReporte.addActionListener(new ActionListener() {
 	        public void actionPerformed(ActionEvent e) {
 	        	try {
 	        		abrirStock();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
 	        }
 	    });
    	 
    	 botonReporteVentas.addActionListener(new ActionListener() {
 	        public void actionPerformed(ActionEvent e) {
 	        	try {
 	        		abrirReporteVentas();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
 	        }
 	    });
    	
    }
  

    private void abrirAltaMercaderias() throws SQLException {
        ingresoMercaderias = new AltaMercaderia();
        ingresoMercaderias.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ingresoMercaderias.setVisible(true);
    }

    private void abrirBajaMercaderias() throws SQLException {
        bajaMercaderias = new BajaMercaderia();
        bajaMercaderias.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bajaMercaderias.setVisible(true);
    }

    private void abrirStock() throws SQLException {
        reporte = new ReporteStock();
        reporte.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reporte.setVisible(true);
    }
    
    private void abrirReporteVentas() throws SQLException {
        reporteVentas = new ReporteVentas();
        reporteVentas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reporteVentas.setVisible(true);
    }

    public static void main(String[] args) {
 		EventQueue.invokeLater(new Runnable() {
 			public void run() {
 				try {
 					MenuFrame frame = new MenuFrame();
 					frame.setVisible(true);
 				} catch (Exception e) {
 					e.printStackTrace();
 				}
 			}
 		});
 	}
}

