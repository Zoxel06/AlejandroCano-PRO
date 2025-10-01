package ejercicio3.controller;

import java.util.ArrayList;
import java.util.Scanner;

public class Lista {

    static Scanner scanner = new Scanner(System.in);
    ArrayList<Datos> listaAlumnos;

    public Lista() {
        listaAlumnos = new ArrayList<>();
    }

    public void agregarPersona() {
        System.out.println("Introduce nombre");
        scanner = new Scanner(System.in);
        String nombre = scanner.next();
        System.out.println("Introduce apellido");
        String apellido = scanner.next();
        System.out.println("Introduce teléfono");
        int telefono = scanner.nextInt();
        System.out.println("Introduce dni");
        String dni = scanner.next();
        System.out.println("Introduce disponibilidad");
        boolean disponibilidad = scanner.hasNext();

        Datos datos = new Datos();
        datos.nombre = nombre;
        datos.apellido = apellido;
        datos.telefono = telefono;
        datos.dni = dni;
        datos.disponibilidad = disponibilidad;

        listaAlumnos.add(datos);

        System.out.println("Alumno agregado correctamente");
    }


    public void buscarPersona() {

        if (listaAlumnos.isEmpty()) {
            System.out.println("La lista de alumnos está vacía");
        } else {


            System.out.println("Introduce el dni del alumno que quieras buscar");
            scanner = new Scanner(System.in);
            String dni = scanner.next();


            for (int i = 0; i < listaAlumnos.size(); i++) {
                if (listaAlumnos.get(i).dni.equals(dni)) {
                    System.out.println("Alumno encontrado:");

                    System.out.println("Nombre: " + listaAlumnos.get(i).nombre);
                    System.out.println("Apellido: " + listaAlumnos.get(i).apellido);
                    System.out.println("Teléfono: " + listaAlumnos.get(i).telefono);
                    System.out.println("DNI: " + listaAlumnos.get(i).dni);
                    System.out.println("Disponibilidad: " + listaAlumnos.get(i).disponibilidad);
                }
            }

            for (int i = 0; i < listaAlumnos.size(); i++) {

                if (!listaAlumnos.get(i).dni.equals(dni)) {
                    System.out.println("No se ha registrado ningún alumno con ese dni");
                }
            }
        }
    }

    public void borrarPersona() {

        if (listaAlumnos.isEmpty()) {
            System.out.println("La lista de alumnos está vacía");
        } else {

            System.out.println("Introduce el dni del alumno que quieras borrar");
            scanner = new Scanner(System.in);
            String dni = scanner.next();

            for (int i = 0; i < listaAlumnos.size(); i++) {
                if (listaAlumnos.get(i).dni.equals(dni)) {
                    System.out.println("Alumno borrado correctamente");
                    ;
                    listaAlumnos.remove(i);
                } else {
                    System.out.println("No se ha registrado ningún alumno con este dni");
                }
            }
        }

    }

    public void borrarSinDisponibilidad() {

        if (listaAlumnos.isEmpty()) {
            System.out.println("La lista de alumnos está vacía");
        } else {

            for (int i = 0; i < listaAlumnos.size(); i++) {

                if (!listaAlumnos.get(i).disponibilidad) {
                    for (Datos item : listaAlumnos) {
                        System.out.println("Todos los alumnos sin disponibilidad han sido eliminados de la lista");
                        listaAlumnos.remove(i);
                    }
                } else {
                    System.out.println("Todos los alumnos registrados tienen disponibilidad");
                }
            }

        }

    }

    public void listarPersonas() {

        if (listaAlumnos.isEmpty()) {
            System.out.println("La lista de alumnos está vacía");
        } else {

            for (int i = 0; i < listaAlumnos.size(); i++) {


                    System.out.println("Alumno " + (i + 1));
                    System.out.println("Nombre: " + listaAlumnos.get(i).nombre);
                    System.out.println("Apellido: " + listaAlumnos.get(i).apellido);
                    System.out.println("Teléfono: " + listaAlumnos.get(i).telefono);
                    System.out.println("DNI: " + listaAlumnos.get(i).dni);
                    System.out.println("Disponibilidad: " + listaAlumnos.get(i).disponibilidad);

            }
        }
    }

}