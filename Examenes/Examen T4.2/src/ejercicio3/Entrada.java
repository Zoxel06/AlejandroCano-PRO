package ejercicio3;

import ejercicio3.controller.Lista;

import java.util.Scanner;

public class Entrada {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Lista listaAlumnos = new Lista();

        int opcion = 0;

        do {
            System.out.println("1. Agregar persona");
            System.out.println("2. Buscar persona");
            System.out.println("3. Borrar persona");
            System.out.println("4. Borrar sin disponibilidad");
            System.out.println("5. Listar personas");
            System.out.println("6. Salir");
            System.out.println("Elije una opción:");
            scanner = new Scanner(System.in);

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    listaAlumnos.agregarPersona();
                    break;
                case 2:
                    listaAlumnos.buscarPersona();
                    break;
                case 3:
                    listaAlumnos.borrarPersona();
                    break;
                case 4:
                    listaAlumnos.borrarSinDisponibilidad();
                    break;
                case 5:
                    listaAlumnos.listarPersonas();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no permitida, inténtalo de nuevo");
            }

        } while (opcion != 6);

    }
}
