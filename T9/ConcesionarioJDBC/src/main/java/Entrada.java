import controller.ConcesionarioController;
import dto.CocheDTO;
import model.Coche;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        CocheDTO cocheDTO = new CocheDTO();
        ConcesionarioController concesionarioController = new ConcesionarioController();

        int opcion;

        do {
            System.out.println("Elije una opcion");
            System.out.println("1. Aniadir coche");
            System.out.println("2. Borrar coche");
            System.out.println("3. Consultar coche");
            System.out.println("4. Listar coches");
            System.out.println("5. Exportar coches a CSV");
            System.out.println("6. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Introduce matricula");
                    String matricula = scanner.next();
                    System.out.println("Introduce marca");
                    String marca = scanner.next();
                    System.out.println("Introduce modelo");
                    String modelo = scanner.next();
                    System.out.println("Introduce color");
                    String color = scanner.next();
                    System.out.println("Introduce precio");
                    int precio = scanner.nextInt();
                    cocheDTO.aniadirCoche(new Coche(matricula, marca, modelo, color, precio));
                    break;
                case 2:
                    System.out.println("Inserta el id del coche que quieres borrar");
                    int id = scanner.nextInt();

                    cocheDTO.borrarCoche(id);
                    break;
                case 3:
                    System.out.println("Inserta el id del coche que quieras buscar");

                    id = scanner.nextInt();

                    cocheDTO.consultarCoche(id);
                    break;
                case 4:
                    cocheDTO.listarCoches();
                    break;
                case 5:

                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no permitida, intentalo de nuevo");
                    break;
            }
        } while (opcion != 5);


    }

}
