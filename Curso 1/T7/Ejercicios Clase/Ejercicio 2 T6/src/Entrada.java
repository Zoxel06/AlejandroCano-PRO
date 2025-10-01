import controller.Empresa;

import java.util.Scanner;

public class Entrada {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Empresa empresa = new Empresa();

        int opcion = 0;

        do {

        System.out.println("Elije una opcion");
        System.out.println("1. Agregar trabajador");
        System.out.println("2. Listar trabajadores");
        System.out.println("3. Mostrar datos");
        System.out.println("4. Salir");

        opcion = scanner.nextInt();

        switch (opcion){
            case 1:
                empresa.registrarTrabajador();
                break;

            case 2:
                empresa.listarTrabajadores();
                break;

            case 3:
                System.out.println("Dime el dni");
                String dni = scanner.next();
                empresa.mostrarInformacion(dni);
                break;

            case 4:
                System.out.println("Saliendo...");
                break;

            default:
                System.out.println("Opcion no permitida");
        }

        }while (opcion != 4);

    }


}
