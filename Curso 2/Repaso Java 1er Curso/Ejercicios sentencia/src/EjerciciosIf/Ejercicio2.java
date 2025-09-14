package EjerciciosIf;

import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = 0;

        System.out.println("Introduce un numero entero");
        n = scanner.nextInt();

        if (n%20 == 0 && n > -100 && n < 100) {
            System.out.println("Es múltiplo de 20 y está entre -100 y 100");
        } else if (n%20 == 0) {
            System.out.println("Es múltiplo de 20 pero no está entre -100 y 100");
        } else if (n > -100 && n < 100) {
            System.out.println("No es múltiplo de 20 pero está entre -100 y 100");
        } else {
            System.out.println("No es múltiplo de 20 y no está entre -100 y 100");
        }

    }

}
