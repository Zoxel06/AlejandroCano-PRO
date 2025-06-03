package dto;

import database.ConnectorDB;
import database.SchemaDB;
import model.Profesor;
import model.ProfesorExterno;
import model.ProfesorInterno;

import javax.print.attribute.standard.SheetCollate;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProfesorDTO {
static Scanner scanner = new Scanner(System.in);

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ArrayList<Profesor> listaProfesores = new ArrayList<>();

    public ProfesorDTO(){

    }

    public void exportarProfesores(Profesor profesor){
        String query = "INSERT INTO profesores (nombre, dni, salario_total) VALUES (?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,profesor.getNombre());
            preparedStatement.setString(2,profesor.getDni());
            preparedStatement.setDouble(3,profesor.getSalarioAnual());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error en la conexion");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error en el cerrado");
            }
        }
    }

    public void aniadirProfesor(Profesor profesor){
        listaProfesores.add(profesor);
        System.out.println("Profesor añadido correctamente");
    }

    public void eliminarProfesor(String dni){

        if (listaProfesores.contains(dni)){
            for (Profesor profesor : listaProfesores) {
                if (dni.equalsIgnoreCase(profesor.getDni())){
                    listaProfesores.remove(profesor);
                    System.out.println("Profesor borrado correctamente");
                }
            }
        } else {
            System.out.println("No se ha encontrado un profesor con ese dni");
        }

    }

    public void mostrarProfesores(){
        String query = "SELECT * FROM profesores;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                for (int i = 0; i < listaProfesores.size(); i++) {
                    System.out.println(listaProfesores.get(i).getNombre());
                    System.out.println(listaProfesores.get(i).getDni());
                    System.out.println(listaProfesores.get(i).getSalarioAnual());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la query");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error en el cerrado");
            }
        }
    }

    public void exportarCsv(){

    }

    public void exportarObj(){

    }

}
