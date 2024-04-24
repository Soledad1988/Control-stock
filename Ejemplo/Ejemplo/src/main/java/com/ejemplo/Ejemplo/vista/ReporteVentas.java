package com.ejemplo.Ejemplo.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.ejemplo.Ejemplo.controller.ReporteVentasController;
import com.ejemplo.Ejemplo.controller.VentaController;
import com.ejemplo.Ejemplo.model.Venta;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ReporteVentas extends JFrame {

    private ReporteVentasController ventaController;
    private JTable tabla;
    private DefaultTableModel modelo;

    public ReporteVentas() throws SQLException {
        super("Reporte de Ventas");

        this.ventaController = new ReporteVentasController();

        // Configuración del diseño del formulario
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 204, 204)); // Color de fondo

        // Configurar tabla de contenido
        configurarTablaDeContenido();

        // Configuración de la ventana
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Generar el reporte al iniciar la ventana
        generarReporte();
    }

    private void configurarTablaDeContenido() {
        tabla = new JTable();
        modelo = new DefaultTableModel();
        modelo.addColumn("id");
        modelo.addColumn("id");
        modelo.addColumn("Código Planta");
        modelo.addColumn("Nombre Planta");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Fecha Venta");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Total Venta");

        tabla.setModel(modelo);

        // Ocultar las columnas de ID
        ocultarColumna(0); // Para el ID de la venta
        ocultarColumna(0); // Para el ID de la planta

        JScrollPane scrollPane = new JScrollPane(tabla);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void generarReporte() throws SQLException {
        // Limpiar tabla antes de generar el reporte
        limpiarTabla();

        // Obtener los datos del controlador
        List<Object[]> reporteVentas = ventaController.obtenerReporteVentas();

        // Llenar la tabla con los datos obtenidos
        for (Object[] ventaInfo : reporteVentas) {
            modelo.addRow(ventaInfo);
        }
    }

    private void limpiarTabla() {
        modelo.setRowCount(0);
    }

    private void ocultarColumna(int indiceColumna) {
        tabla.removeColumn(tabla.getColumnModel().getColumn(indiceColumna));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ReporteVentas frame = new ReporteVentas();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}