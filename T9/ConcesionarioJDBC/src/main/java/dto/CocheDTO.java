package dto;

import database.DBConnector;
import database.SchemaDB;
import model.Coche;

import java.sql.*;
import java.util.Scanner;

public class CocheDTO {

    static Scanner scanner = new Scanner(System.in);

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CocheDTO() {
        connection = DBConnector.getConnection();
    }

    public void aniadirCoche(Coche coche) {
        try {
            String query = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?,?,?,?,?)", SchemaDB.TAB_CAR, SchemaDB.COL_REGISTRATION, SchemaDB.COL_BRAND,
                    SchemaDB.COL_MODEL, SchemaDB.COL_COLOR, SchemaDB.COL_PRICE);

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, coche.getMatricula());
            preparedStatement.setString(2, coche.getMarca());
            preparedStatement.setString(3, coche.getModelo());
            preparedStatement.setString(4, coche.getColor());
            preparedStatement.setInt(5, coche.getPrecio());
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Error en la query");
            System.out.println(e.getMessage());
        }
    }

    public void borrarCoche(int id){

        String query = String.format("DELETE FROM %s WHERE %s = ?", SchemaDB.TAB_CAR, SchemaDB.COL_ID);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error en la query");
            System.out.println(e.getMessage());
        }
    }

    public void consultarCoche(int id){

        String query = String.format("SELECT * FROM %s WHERE %s = ?", SchemaDB.TAB_CAR, SchemaDB.COL_ID);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error en la query");
        }

    }

    public void listarCoches(){

        String query = String.format("SELECT * FROM %s", SchemaDB.TAB_CAR);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error en la query");
        }
    }

}
