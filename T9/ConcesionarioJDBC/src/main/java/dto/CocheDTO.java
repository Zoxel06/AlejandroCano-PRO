package dto;

import database.DBConnector;
import database.SchemaDB;
import model.Coche;

import java.sql.*;

public class CocheDTO {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CocheDTO() {
        connection = DBConnector.getConnection();
    }

    public void aniadirCoche(Coche coche) {
        try {
            statement = connection.createStatement();
            String query = "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?,?,?,?,?)";
            String queryFormateada =String.format(query, SchemaDB.TAB_CAR, SchemaDB.COL_REGISTRATION, SchemaDB.COL_BRAND,
                    SchemaDB.COL_MODEL, SchemaDB.COL_COLOR, SchemaDB.COL_PRICE);

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, coche.getMatricula());
            preparedStatement.setString(2, coche.getMarca());
            preparedStatement.setString(3, coche.getModelo());
            preparedStatement.setString(4, coche.getColor());
            preparedStatement.setInt(5, coche.getPrecio());

        } catch (SQLException e) {
            System.out.println("Error en la query");
            System.out.println(e.getMessage());
        }
    }
}
