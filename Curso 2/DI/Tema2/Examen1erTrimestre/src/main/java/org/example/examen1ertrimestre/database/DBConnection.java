package org.example.examen1ertrimestre.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() {

        if (connection == null) {
            createConnection();
        }

        return connection;
    }

    private static void createConnection() {
        String user = "root";
        String pass = "root";
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s",
                    SchemeDB.URL, SchemeDB.PORT, SchemeDB.DB_NAME),user,pass);
        } catch (SQLException e) {
            System.out.println("Error en la conexion con la base de datos");
            System.out.println(e.getMessage());
        }
    }
}