package EjerciciosSwitch;

import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce un operador");
        char operador = scanner.next().charAt(0);

        System.out.println("Introduce el primer número");
        float n1 = scanner.nextFloat();

        System.out.println("Introduce el segundo número");
        float n2 = scanner.nextFloat();

        float resultado = 0;

        switch (operador) {
            case '+':
                resultado = n1 + n2;
                System.out.println(resultado);
                break;
            case '-':
                resultado = n1 - n2;
                System.out.println(resultado);
                break;
            case '*':
                resultado = n1 * n2;
                System.out.println(resultado);
                break;
            case '/':
                resultado = n1 / n2;
                System.out.println(resultado);
                break;
        }

    }

}
