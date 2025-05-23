package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null){
            createConnection();
        }
        return connection;
    }

    public static void createConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql//localhost:3306/pasajeros_ces","root","pwd1");
        } catch (SQLException e) {
            System.out.println("Error al crear conexion");
            System.out.println(e.getMessage());
        }
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexion");
            System.out.println(e.getMessage());
        }
    }

}
