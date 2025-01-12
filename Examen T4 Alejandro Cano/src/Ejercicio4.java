import java.util.Objects;
import java.util.Scanner;

public class Ejercicio4 {
    static Scanner scanner = new Scanner(System.in);

    static String nombre = "", apellido = "", dni = "", dniBuscar = "";
    static int telefono = 0;

    public static void main(String[] args) {

        Objects[][] datos = new Objects[10][4];

        int opcion = 0;

        do {

            System.out.println("1. Agregar persona");
            System.out.println("2. Buscar persona");
            System.out.println("3. Listar persona");
            System.out.println("4. Salir");
            System.out.println("Selecciona una opcion");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarPersona();
                    break;
                case 2:
                    buscarPersona();
                    break;
                case 3:
                    listarPersona();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida, introduzca una opcion existente");
                    break;
            }

        } while (opcion != 4);

    }


    public static void agregarPersona() {
        System.out.println("Introduce nombre");
        nombre = scanner.next();
        System.out.println("Introduce apellido");
        apellido = scanner.next();
        System.out.println("Introduce numero de telefono");
        telefono = scanner.nextInt();
        System.out.println("Introduce dni");
        dni = scanner.next();


    }


    public static void buscarPersona() {
        System.out.println("Introduce el dni de la persona que quieras buscar");
        dniBuscar = scanner.next();

        if (Objects.equals(dniBuscar, dni)){
            System.out.println("Nombre :" + nombre);
            System.out.println("Apellido :" + apellido);
            System.out.println("Telefono :" + telefono);
        }else {
            System.out.println("El dni no se ha registrado o no existe");
        }
    }


    public static void listarPersona() {
        System.out.println("Nombre :" + nombre);
        System.out.println("Apellido :" + apellido);
        System.out.println("Telefono :" + telefono);
        System.out.println("DNI :" + dni);
    }
}
