package controller;

import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {

    private static Scanner scanner = new Scanner(System.in);
    private ArrayList<Object[]> listaContactos;

    // constructor
    public Agenda() {
        System.out.println("Iniciando la agenda");

        listaContactos = new ArrayList<>();
    }
    // private static void "nombre"(parámetros(lo que voy a usar)){}


    public boolean agregarPersona(String nombre, String apellido, int telefono, String dni) {
        Object[] persona = {nombre, apellido, telefono, dni};


        if (estaPersona(dni) == null) {
            listaContactos.add(persona);
            return true;
        }

        return false;
    }

    public Object[] estaPersona(String dni) {
        for (Object[] item : listaContactos) {

            if (String.valueOf(item[3]).equalsIgnoreCase(dni)) {
                return item;
            }
        }
        return null;
    }

    public int buscarPersona(String dni) {
        if (listaContactos.isEmpty()) {
            //la lista esta vacia
            return 1;
        } else if (buscarPersona(dni) == null) {
            //la lista no esta vacia pero el usuario no esta
            return 2;
        }
        return 3; //no pongo else porque no hace falta, si no se entra en los dos casos anteriores ya va a pasar por el return 3
}
