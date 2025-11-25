package org.example.pizzeria.dao;

import org.example.pizzeria.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    void insertarUsuario(Usuario usuario);

    List<Usuario> obtenerUsuarios();

    int borrarUsuarios(String nombre);

}
