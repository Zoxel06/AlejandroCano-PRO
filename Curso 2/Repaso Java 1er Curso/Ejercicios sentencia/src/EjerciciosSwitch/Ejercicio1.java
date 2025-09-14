package EjerciciosSwitch;

import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce una letra");
        char letra = scanner.next().charAt(0);

        switch (letra) {
            case 'a','e','i','o','u':
                System.out.println("Es una vocal");
            default:
                if (letra != 'a' && letra !='e'&& letra !='i'&& letra !='o'&& letra !='u') {
                    System.out.println("Es una consonante");
                }
        }

    }

}
