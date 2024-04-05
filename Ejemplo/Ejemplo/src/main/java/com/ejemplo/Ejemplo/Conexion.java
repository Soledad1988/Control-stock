package com.ejemplo.Ejemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	 private static final String URL = "jdbc:mysql://localhost/stock?useTimeZone=true&serverTimeZone=UTC";
	    private static final String USER = "root";
	    private static final String PASSWORD = "*********";

	    public static Connection obtenerConexion() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
