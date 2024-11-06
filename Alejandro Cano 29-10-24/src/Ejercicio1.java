import java.util.Scanner;

public class Ejercicio1 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Dime el primer operando");
        double operando1 = scanner.nextInt();
        System.out.println("Dime el segundo operando");
        double operando2 = scanner.nextInt();

        int opcion = 0;

        do {


        if (operando1 > -1 && operando2 > -1) {
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Salir");
            System.out.println("Elige una opcion");

            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("La suma de los numeros es igual a " + (int)(operando1 + operando2));
                    break;
                case 2:
                    System.out.println("La resta de los numeros es igual a " + (int)(operando1 - operando2));
                    break;
                case 3:
                    System.out.println("La multiplicacion de los numeros es igual a " + (int)(operando1 * operando2));
                    break;
                case 4:
                    System.out.println("La division de los numeros es igual a " + (operando1 / operando2));
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Esta opcion no está permitida");
            }

        } else {
            System.out.println("No se pueden realizar operaciones sobre numeros negativos, vuelve a introducir los numero en positivo");
        }

        } while (opcion != 5);

    }
}
