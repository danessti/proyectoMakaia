package com.example.ProyectoIntegradorMakaia.ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/sistema_vuelo";
        String username = "root";
        String password = "08647"; // Cambiar clave de mysql local

        try {
            // Cargue el controlador MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexion
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Si la conexion es exitosa, imprime un mensaje de exito
            System.out.println("Conectando a la base de datos");

            // Aquí cerramos la conexion al haber terminado la prueba
            connection.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Controlador MySQL JDBC no encontrado.");
        } catch (SQLException e) {
            System.out.println("La conexion a la base de datos fallo. Error: " + e.getMessage());
        }
    }
}