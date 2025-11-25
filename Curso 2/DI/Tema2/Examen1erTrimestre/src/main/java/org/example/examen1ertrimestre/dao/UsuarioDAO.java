package org.example.examen1ertrimestre.dao;

import org.example.examen1ertrimestre.database.DBConnection;
import org.example.examen1ertrimestre.database.SchemeDB;
import org.example.examen1ertrimestre.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public UsuarioDAO() {
        connection = DBConnection.getConnection();
    }

    public void realizarInsercion(Usuario usuario){
        try {
            String query = "INSERT INTO usuarios (nombre, correo, pass) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, SchemeDB.COL_NOMBRE_USUARIO);
            preparedStatement.setString(2, SchemeDB.COL_EMAIL_USUARIO);
            preparedStatement.setString(3, SchemeDB.COL_PASSWORD_USUARIO);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            preparedStatement.getConnection().close();

            System.out.println("Usuario insertado correctamente");
        } catch (Exception e) {
            e.getMessage();
        }
    }


}
