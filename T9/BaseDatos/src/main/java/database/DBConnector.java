package database;

import com.mysql.jdbc.Driver;

import java.nio.file.attribute.UserPrincipal;
import java.sql.*;
import java.util.Map;

public class DBConnector {

    private static Connection connection;

    public static Connection getConnection(){

        if (connection == null){
            createConnection();
        }

        return connection;
    }

    public static void createConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/concesionario_ces","root","pwd1");
        } catch (SQLException e) {
            System.out.println("Error en la conexión");
        }
    }

}
