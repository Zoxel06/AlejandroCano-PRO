import java.util.Scanner;

public class Entrada {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;

        System.out.println("Elije una opcion");
        System.out.println("1. Aniadir coche");
        System.out.println("2. Borrar coche");
        System.out.println("3. Consultar coche");
        System.out.println("4. Listar coches");
        System.out.println("5. Salir");

        opcion = scanner.nextInt();

        switch (opcion){
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opcion no permitida, intentalo de nuevo");
                break;
        }

    }

}
