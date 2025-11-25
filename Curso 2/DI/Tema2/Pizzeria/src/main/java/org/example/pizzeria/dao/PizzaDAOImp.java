package org.example.pizzeria.dao;

import org.example.pizzeria.database.DBConnection;
import org.example.pizzeria.database.SchemeDB;
import org.example.pizzeria.model.Pizza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PizzaDAOImp implements PizzaDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public PizzaDAOImp() {
        connection = DBConnection.getConnection();
    }

    @Override
    public List<Pizza> obtenerPizzas() {

        List<Pizza> pizzas = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM " + SchemeDB.TAB_PIZZA +
                            " GROUP BY " + SchemeDB.PIZ_COL_NOMBRE
            );

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Pizza pizza = new Pizza();
                pizza.setNombre(rs.getString(SchemeDB.PIZ_COL_NOMBRE));
                pizzas.add(pizza);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener pizzas");
            System.out.println(e.getMessage());
        }

        return pizzas;
    }

    @Override
    public Pizza obtenerPizza(String nombre, String tamano) throws SQLException {

        String query = String.format(
                "SELECT * FROM %s WHERE %s = ? AND %s = ?",
                SchemeDB.TAB_PIZZA,
                SchemeDB.PIZ_COL_NOMBRE,
                SchemeDB.PIZ_COL_TAMANIO
        );

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nombre);
        preparedStatement.setString(2, tamano);

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {

            int id = rs.getInt(SchemeDB.PIZ_COL_ID);
            double precio = rs.getDouble(SchemeDB.PIZ_COL_PRECIO);

            return new Pizza(nombre, tamano, precio);
        }

        return null;
    }
}
