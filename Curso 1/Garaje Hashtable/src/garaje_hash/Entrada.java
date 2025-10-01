package garaje_hash;

import garaje_hash.controller.Garaje;

import java.util.Scanner;

public class Entrada {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Garaje garaje = new Garaje();

        int opcion = 0;

        System.out.println("Bienvenido a tu garaje personal");

        do {

            System.out.println("1. Añadir coche");
            System.out.println("2. Listar coche");
            System.out.println("3. Buscar coche");
            System.out.println("4. Mostrar costes");
            System.out.println("5. Eliminar coche");
            System.out.println("6. Vaciar garaje");
            System.out.println("7. Salir");
            System.out.println("Elije una opción");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 7);



        /*
        garaje.agregarCoche();
        garaje.modificarCoche("1234C");
        garaje.getCoche("1234C");
        garaje.recorrerCochesElement();
        */
    }

}