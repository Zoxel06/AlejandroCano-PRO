package dto;

import database.DBConnector;
import database.SchemaDB;
import model.Coche;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public void borrarCoche(int id) {

        String query = String.format("DELETE FROM %s WHERE %s = ?", SchemaDB.TAB_CAR, SchemaDB.COL_ID);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la query");
            System.out.println(e.getMessage());
        }
    }

    public void consultarCoche(int id) {

        String query = String.format("SELECT * FROM %s WHERE %s = ?", SchemaDB.TAB_CAR, SchemaDB.COL_ID);

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt(SchemaDB.COL_ID);
                String matricula = resultSet.getString(SchemaDB.COL_REGISTRATION);
                String marca = resultSet.getString(SchemaDB.COL_BRAND);
                String modelo = resultSet.getString(SchemaDB.COL_MODEL);
                String color = resultSet.getString(SchemaDB.COL_COLOR);
                int precio = resultSet.getInt(SchemaDB.COL_PRICE);

                System.out.printf("Id: %d, Matricula: %s, Marca: %s, Modelo: %s, Color: %s, Precio: %s",
                        id, matricula, marca, modelo, color, precio);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error en la query");
        }

    }

    public void listarCoches() {

        String query = String.format("SELECT * FROM %s", SchemaDB.TAB_CAR);

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(SchemaDB.COL_ID);
                String matricula = resultSet.getString(SchemaDB.COL_REGISTRATION);
                String marca = resultSet.getString(SchemaDB.COL_BRAND);
                String modelo = resultSet.getString(SchemaDB.COL_MODEL);
                String color = resultSet.getString(SchemaDB.COL_COLOR);
                int precio = resultSet.getInt(SchemaDB.COL_PRICE);

                System.out.printf("Id: %d, Matricula: %s, Marca: %s, Modelo: %s, Color: %s, Precio: %d",
                        id, matricula, marca, modelo, color, precio);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error en la query");
        }
    }


    public void exportarCsv(){

        File file = new File("src/main/java/resources/coches.csv");
        ArrayList<Coche> listaCoches = new ArrayList<>();
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Id;Matricula;Marca;Modelo;Color;Precio");

            for (Coche coche : listaCoches){
                fileWriter.write(coche.getId() + ";" +
                        coche.getMatricula() + ";" +
                        coche.getMarca() + ";" +
                        coche.getModelo() + ";" +
                        coche.getColor() + ";" +
                        coche.getPrecio() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error en la escritura");
        }

    }

}
