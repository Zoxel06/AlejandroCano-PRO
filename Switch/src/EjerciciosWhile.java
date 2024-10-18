import java.util.Scanner;

public class EjerciciosWhile {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //ejercicioClaseWhile();
        //ejercicioClaseDoWhile();
        ejercicioOpciones();

    }

    public static void ejercicioClaseWhile() {
        //PEDIR POR CONSOLA NUMEROS HASTA QUE EL USUARIO INTRODUZCA UN 0
        System.out.println("Adivina el numero que estoy pensando");
        int n = scanner.nextInt();
        int i = 0;
        while (n != i) {
            System.out.println("Ese no es el numero que estoy pensando");
            System.out.println("Introduce otro numero distinto");
            n = scanner.nextInt();

        }
        System.out.println("Muy bien, ese era el numero que estaba pensando");
    }

    public static void ejercicioClaseDoWhile() {
        int numero;
        int i = 0;
        do {
            System.out.println("Introduce un numero");
            numero = scanner.nextInt();
            i++;
        } while (numero != 0);
        System.out.println("Muy bien, has acertado el numero");
        System.out.println("Has necesitado " + i + " intentos");
    }

    public static void ejercicioOpciones() {
        int opcion;
        do {
            System.out.println("1.Opcion añadir\n2.Opcion borrar\n3.Opcion listar\n4.Opcion buscar\n5.Salir\n¿Que quieres hacer?:");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Añadir");
                    break;
                case 2:
                    System.out.println("Borrar");
                    break;
                case 3:
                    System.out.println("Listar");
                    break;
                case 4:
                    System.out.println("Bbuscar");
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Esa opcion no esta entre las mostradas");
            }
        } while (opcion != 5);

        System.out.println("Terminando programa");
    }
}
