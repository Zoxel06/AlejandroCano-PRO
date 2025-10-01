package hotel_arraylist;

import hotel_arraylist.controller.Hotel;

import java.util.Scanner;

public class Entrada {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        int opcion = 0;

        do {
            System.out.println("1. Registrar reserva");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Mostrar reservas actuales");
            System.out.println("4. Mostrar reservas ordenadas");
            System.out.println("¿Qué quieres hacer?");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    hotel.registrarReserva(scanner.nextInt(),scanner.next(),scanner.nextInt(),scanner.next(),scanner.nextInt());
                    break;
                case 2:

                    break;
                case 3:
                    hotel.mostrarReservas();
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no permitida");
            }

        } while (opcion != 5);
    }

}
