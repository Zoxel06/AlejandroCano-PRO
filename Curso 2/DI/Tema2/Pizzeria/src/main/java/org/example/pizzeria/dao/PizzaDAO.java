package org.example.pizzeria.dao;

import org.example.pizzeria.model.Pizza;

import java.sql.SQLException;
import java.util.List;

public interface PizzaDAO {

    List<Pizza> obtenerPizzas();

    Pizza obtenerPizza(String nombre, String tamano) throws SQLException;
}
