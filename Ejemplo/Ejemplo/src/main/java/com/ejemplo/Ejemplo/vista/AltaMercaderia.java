package com.ejemplo.Ejemplo.vista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ejemplo.Ejemplo.controller.PlantaController;
import com.ejemplo.Ejemplo.model.Planta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.sql.SQLException;

public class AltaMercaderia extends JFrame {

    private JLabel labelCodigo, labelNombre, labelCantidad, labelPrecio;
    private JTextField textoCodigo, textoNombre, textoCantidad, textoPrecio;
    private JButton botonGuardar, botonEtiqueta;
    private JDateChooser textFechaIngreso;
    private JCheckBox chckbxEstado;
    private JTextField textoPorcentajeGanancia;
    private PlantaController plantaController;

    public AltaMercaderia() throws SQLException {
        super("Alta de Mercadería");

        this.plantaController = new PlantaController();
        // Configuración del fondo rosa claro
        getContentPane().setBackground(new Color(255, 204, 204));

        // Configuración del diseño del formulario
        getContentPane().setLayout(null); // Usaremos disposición absoluta

        labelCodigo = new JLabel("Código:");
        labelCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelCodigo.setBounds(22, 52, 95, 25);

        labelNombre = new JLabel("Nombre:");
        labelNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelNombre.setBounds(22, 92, 95, 25);

        labelCantidad = new JLabel("Cantidad:");
        labelCantidad.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelCantidad.setBounds(22, 132, 95, 25);

        labelPrecio = new JLabel("Precio:");
        labelPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelPrecio.setBounds(22, 172, 95, 25);

        textoCodigo = new JTextField();
        textoCodigo.setBounds(147, 52, 128, 25);

        textoNombre = new JTextField();
        textoNombre.setBounds(147, 92, 128, 25);

        textoCantidad = new JTextField();
        textoCantidad.setBounds(147, 132, 128, 25);

        textoPrecio = new JTextField();
        textoPrecio.setBounds(147, 172, 128, 25);

        botonGuardar = new JButton("Guardar");
        botonGuardar.setBounds(176, 324, 95, 25);
        botonGuardar.setBackground(Color.GRAY); // Establecer el color de fondo del botón
        botonGuardar.setForeground(Color.WHITE); // Establecer el color del texto del botón
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\brent\\Downloads\\planta.jpg"));
        lblNewLabel.setBounds(341, 29, 169, 185);
        getContentPane().add(lblNewLabel);
        
        botonEtiqueta = new JButton("Imprimir Etiqueta");
        botonEtiqueta.setBounds(281, 324, 148, 25);
        botonEtiqueta.setBackground(Color.GRAY); // Establecer el color de fondo del botón
        botonEtiqueta.setForeground(Color.WHITE);
        botonEtiqueta.setVisible(false); // Ocultar el botón imprimir inicialmente
        getContentPane().add(botonEtiqueta);
        


        // Agregar componentes al contenedor
        Container container = getContentPane();
        container.add(labelCodigo);
        container.add(labelNombre);
        container.add(labelCantidad);
        container.add(labelPrecio);
        container.add(textoCodigo);
        container.add(textoNombre);
        container.add(textoCantidad);
        container.add(textoPrecio);
        container.add(botonGuardar);
        
        JLabel labelEstado = new JLabel("Estado:");
        labelEstado.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelEstado.setBounds(22, 254, 95, 25);
        getContentPane().add(labelEstado);
        
        chckbxEstado = new JCheckBox("Activo");
        chckbxEstado.setBounds(148, 256, 127, 23);
        getContentPane().add(chckbxEstado);
        
        JLabel labelFecha = new JLabel("Fecha:");
        labelFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelFecha.setBounds(22, 11, 95, 25);
        getContentPane().add(labelFecha);
        
        textFechaIngreso = new JDateChooser();
        textFechaIngreso.setDateFormatString("dd-MM-yyyy");
        textFechaIngreso.getCalendarButton().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        textFechaIngreso.getCalendarButton().setBackground(SystemColor.textHighlight);
        textFechaIngreso.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
        textFechaIngreso.setBounds(147, 11, 128, 30);
        textFechaIngreso.getCalendarButton().setBounds(268, 0, 21, 33);
        textFechaIngreso.setBackground(Color.WHITE);
        textFechaIngreso.setBorder(new LineBorder(SystemColor.window));
        textFechaIngreso.setDateFormatString("yyyy-MM-dd");
        textFechaIngreso.setFont(new Font("Roboto", Font.PLAIN, 18));
		//panel.add(textFechaIngreso);
        getContentPane().add(textFechaIngreso);
        
        JLabel labelPorcentajeGanancia = new JLabel("Porcentaje de Ganancia:");
        labelPorcentajeGanancia.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelPorcentajeGanancia.setBounds(22, 208, 185, 25);
        getContentPane().add(labelPorcentajeGanancia);

        textoPorcentajeGanancia = new JTextField();
        textoPorcentajeGanancia.setBounds(208, 210, 66, 25);
        getContentPane().add(textoPorcentajeGanancia);
        

     // Configuración de la ventana
        setSize(572, 397); // Se ha ajustado el ancho de la ventana
        setLocationRelativeTo(null); // Centrar ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        configurarAccionesDelFormulario();
        
    }
    
    
    
    private void configurarAccionesDelFormulario() {
    	   
    	 botonGuardar.addActionListener(new ActionListener() {
    	        public void actionPerformed(ActionEvent e) {
    	            guardar();
    	        }
    	    });

    	 botonEtiqueta.addActionListener(new ActionListener() {
    	        public void actionPerformed(ActionEvent e) {
    	            imprimirEtiqueta();
    	        }
    	    });
    	
    }
  
    private void guardar() {
    	 
        try {
        	String fechaIngreso = ((JTextField) textFechaIngreso.getDateEditor().getUiComponent()).getText();
            Integer codigo = Integer.parseInt(textoCodigo.getText());
            String nombrePlanta = textoNombre.getText();
            Integer cantidad = Integer.parseInt(textoCantidad.getText());
            Double precioCosto = Double.parseDouble(textoPrecio.getText());
            boolean activo = chckbxEstado.isSelected(); // Obtener el estado activo desde el checkbox
            double porcentajeGanancia = Double.parseDouble(textoPorcentajeGanancia.getText()); // Obtener el porcentaje de ganancia

         // Calcular el precio de venta
            double precioVenta = precioCosto * (1 + (porcentajeGanancia / 100));
            
            // Redondear el precio de venta a dos decimales
            DecimalFormat df = new DecimalFormat("#.##");
            precioVenta = Double.valueOf(df.format(precioVenta));
            
            Planta planta = new Planta(java.sql.Date.valueOf(fechaIngreso), codigo, nombrePlanta, cantidad, precioCosto, precioVenta, activo);
            this.plantaController.guardar(planta);

            // Mostrar un mensaje de confirmación
            JOptionPane.showMessageDialog(this, "La planta ha sido guardada correctamente", "Planta Guardada", JOptionPane.INFORMATION_MESSAGE);
        
            // Después de guardar correctamente, mostramos el botón de imprimir
            botonEtiqueta.setVisible(true);
            
        } catch (NumberFormatException e) {
            // Manejar el caso en que los valores ingresados no sean números válidos
            JOptionPane.showMessageDialog(this, "Los valores ingresados no son válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void imprimirEtiqueta() {
        // Obtener los datos de la mercadería
        String codigo = textoCodigo.getText();
        String nombre = textoNombre.getText();
        double precio = Double.parseDouble(textoPrecio.getText()); // No necesitarás esta línea

        // Crear el documento PDF
        Document document = new Document();
        try {
            // Obtener la ubicación del directorio de documentos del usuario
            String rutaDirectorioUsuario = System.getProperty("user.home") + "\\OneDrive\\Escritorio\\etiquetas\\";

            // Verificar si la carpeta existe, si no existe, crearla
            File carpeta = new File(rutaDirectorioUsuario);
            if (!carpeta.exists()) {
                carpeta.mkdirs(); // Crear la carpeta y sus subdirectorios si no existen
            }

            // Generar el nombre del archivo PDF con marca de tiempo para diferenciar
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String marcaTiempo = formatoFecha.format(new Date());
            String nombreArchivo = "etiqueta_" + marcaTiempo + ".pdf";
            String rutaArchivo = rutaDirectorioUsuario + nombreArchivo;

            // Especificar la ubicación del archivo PDF
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));

            // Abrir el documento
            document.open();

            // Agregar los datos de la mercadería al documento
            document.add(new Paragraph("Código: " + codigo));
            document.add(new Paragraph("Nombre: " + nombre));
            // Obtener el precio de venta de la entidad Planta
            // Obtener el precio de venta desde el DAO
            double precioVenta = this.plantaController.obtenerPrecioVenta(Integer.parseInt(codigo));

            document.add(new Paragraph("Precio de venta: " + precioVenta)); // Imprimir el precio de venta

            // Cerrar el documento
            document.close();
            JOptionPane.showMessageDialog(this, "Imprimiendo etiqueta...");

            // Limpiar el formulario después de imprimir la etiqueta
            limpiarFormulario();

            // Mostrar un mensaje de confirmación
            JOptionPane.showMessageDialog(this, "La etiqueta ha sido impresa correctamente en el archivo '" + nombreArchivo + "' en la carpeta 'etiquetas' del escritorio", "Etiqueta Impresa", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al crear el documento PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error de entrada/salida al escribir en el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarFormulario() {
    	this.textFechaIngreso.setDate(null);
        textoCodigo.setText("");
        textoNombre.setText("");
        textoCantidad.setText("");
        textoPrecio.setText("");
        textoPorcentajeGanancia.setText("");
        // Aquí puedes agregar más campos que necesiten ser limpiados
    }



    public static void main(String[] args) {
 		EventQueue.invokeLater(new Runnable() {
 			public void run() {
 				try {
 					AltaMercaderia frame = new AltaMercaderia();
 					frame.setVisible(true);
 				} catch (Exception e) {
 					e.printStackTrace();
 				}
 			}
 		});
 	}
}

