package campeonato_hashtable;

import campeonato_hashtable.controller.Campeonato;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Campeonato campeonato = new Campeonato();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nCampeonato de Fórmula 1");
            System.out.println("1. Agregar piloto");
            System.out.println("2. Actualizar puntos");
            System.out.println("3. Mostrar información de piloto");
            System.out.println("4. Mostrar clasificación");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del piloto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Bastidor del coche: ");
                    String bastidor = scanner.nextLine();
                    campeonato.agregarPiloto(nombre, bastidor);
                    break;
                case 2:
                    if (campeonato.getCarrerasRestantes() > 0) {
                        System.out.print("Bastidor del piloto para actualizar puntos: ");
                        String bastidorPuntos = scanner.nextLine();
                        campeonato.actualizarPuntos(bastidorPuntos);
                    } else {
                        System.out.println("Ya no quedan carreras para repartir puntos.");
                    }
                    break;
                case 3:
                    System.out.print("Bastidor del piloto para mostrar información: ");
                    String bastidorInfo = scanner.nextLine();
                    campeonato.mostrarInformacionPiloto(bastidorInfo);
                    break;
                case 4:
                    campeonato.mostrarClasificacion();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

    }
}