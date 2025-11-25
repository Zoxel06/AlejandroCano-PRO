package org.example.pizzeria.database;

public interface SchemeDB {

    // Conexión
    String URL = "127.0.0.1";
    String PORT = "41063";
    String DB_NAME = "formularios";

    // -------- TABLAS --------
    String TAB_USUARIO = "usuarios";
    String TAB_PIZZA = "pizzas";

    // -------- USUARIO --------
    String USU_COL_ID = "id";
    String USU_COL_NOMBRE = "nombre";
    String USU_COL_TELEFONO = "telefono";

    // -------- PIZZA --------
    String PIZ_COL_ID = "id";
    String PIZ_COL_NOMBRE = "nombre";
    String PIZ_COL_TAMANIO = "tamaño";
    String PIZ_COL_PRECIO = "precio";

}
