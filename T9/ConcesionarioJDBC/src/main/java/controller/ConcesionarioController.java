package controller;

import database.DBConnector;
import database.SchemaDB;
import dto.CocheDTO;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConcesionarioController {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private CocheDTO cocheDTO;

    public ConcesionarioController(){
        connection = DBConnector.getConnection();
    }

    public void comprobarMatricula(Coche coche){

        String query = String.format("SELECT * FROM %s WHERE %s = ?", SchemaDB.TAB_CAR, SchemaDB.COL_REGISTRATION);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, coche.getMatricula());

            if ((preparedStatement.executeQuery()==null)){
                System.out.println("No se pueden añadir 2 matriculas iguales");
            }else {
                cocheDTO.aniadirCoche(coche);
            }

        } catch (SQLException e) {
            System.out.println("Error en la query");
        }

    }

}
