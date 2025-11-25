package org.example.examen1ertrimestre.database;

public interface SchemeDB {
    // variables -> final static
    // metodos -> sin cuerpo abstractor

    String URL = "127.0.0.1";
    String PORT = "41063";

    String DB_NAME = "formularioProductos";
    String TAB_NAME_USUARIOS = "peticiones";
    String COL_ID_USUARIO = "id";
    String COL_NOMBRE_USUARIO = "nombre";
    String COL_PASSWORD_USUARIO = "pass";
    String COL_EMAIL_USUARIO = "correo";
    String COL_ID_ELEMENTO = "id";
    String COL_NOMBRE_ELEMENTO = "nombre";
    String COL_CATEGORIA_ELEMENTO = "categoria";
    String COL_PRECIO_ELEMENTO = "precio";

}