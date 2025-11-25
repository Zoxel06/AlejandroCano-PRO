package org.example.pizzeria.dao;

import org.example.pizzeria.database.DBConnection;
import org.example.pizzeria.database.SchemeDB;
import org.example.pizzeria.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImp implements UsuarioDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public UsuarioDAOImp() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void insertarUsuario(Usuario usuario) {

        String query = String.format(
                "INSERT INTO %s (%s,%s) VALUES (?,?)",
                SchemeDB.TAB_USUARIO,
                SchemeDB.USU_COL_NOMBRE,
                SchemeDB.USU_COL_TELEFONO
        );

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getTelefono());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Usuario> obtenerUsuarios() {

        List<Usuario> lista = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM " + SchemeDB.TAB_USUARIO
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt(SchemeDB.USU_COL_ID);
                String nombre = resultSet.getString(SchemeDB.USU_COL_NOMBRE);
                String telefono = resultSet.getString(SchemeDB.USU_COL_TELEFONO);

                Usuario usuario = new Usuario(id, nombre, telefono);
                lista.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios");
            System.out.println(e.getMessage());
        }

        return lista;
    }

    @Override
    public int borrarUsuarios(String nombre) {
        return 0;
    }
}
