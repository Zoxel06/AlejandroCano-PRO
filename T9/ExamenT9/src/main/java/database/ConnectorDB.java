package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB {
    private Connection connection;

    public Connection connection(){
        if (connection == null){
            getConnection();
        }
        return connection;
    }

    public void getConnection(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://servidor:3306/profesores_ces", "root", "pwd1");
        } catch (SQLException e) {
            System.out.println("Error al crear conexion");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }


}
