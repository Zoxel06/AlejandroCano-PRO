package org.example.pizzeria.database;

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
        String url = "jdbc:mysql://" + SchemeDB.URL + ":" + SchemeDB.PORT + "/" + SchemeDB.DB_NAME;

        try {
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión a la base de datos establecida.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar a la base de datos.");
        }
    }
}
