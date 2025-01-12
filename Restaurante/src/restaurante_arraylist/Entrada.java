package restaurante_arraylist;

import restaurante_arraylist.controller.Restaurante;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nGestión de Mesas en el Restaurante");
            System.out.println("1. Asignar mesa");
            System.out.println("2. Liberar mesa");
            System.out.println("3. Realizar pedido");
            System.out.println("4. Mostrar mesas ocupadas");
            System.out.println("5. Mostrar mesas ordenadas por precio");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Número de mesa: ");
                    int numeroMesa = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Nombre del cliente: ");
                    String nombreCliente = scanner.nextLine();
                    restaurante.asignarMesa(numeroMesa, nombreCliente);
                    break;
                case 2:
                    System.out.print("Número de mesa a liberar: ");
                    int mesaLiberar = scanner.nextInt();
                    restaurante.liberarMesa(mesaLiberar);
                    break;
                case 3:
                    System.out.print("Número de mesa: ");
                    int mesaPedido = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Producto a pedir: ");
                    String producto = scanner.nextLine();
                    restaurante.realizarPedido(mesaPedido, producto);
                    break;
                case 4:
                    restaurante.mostrarMesasOcupadas();
                    break;
                case 5:
                    restaurante.mostrarMesasOrdenadasPorPrecio();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);


    }
}