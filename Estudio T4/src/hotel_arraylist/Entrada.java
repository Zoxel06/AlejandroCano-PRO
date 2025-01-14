package hotel_arraylist;

import hotel_arraylist.controller.Hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {

    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        Hotel hotel = new Hotel();
        int opcion = 0;


        do {

            System.out.println("----------------------");
            System.out.println("1. Registrar reserva");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Mostrar reservas actuales");
            System.out.println("4. Mostrar reservas ordenadas");
            System.out.println("5. Salir");
            System.out.println("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    hotel.agregarPersona();
                    break;
                case 2:
                    hotel.cancelarReserva();
                    break;
                case 3:
                    hotel.mostrarReserva();
                    break;
                case 4:
                    //hotel.ordenarReservasCollections();
                    hotel.ordenarReservasBurbuja();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;

            }
        } while (opcion != 5);

    }
}
