package EjerciciosFor;

import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el número de temperaturas");
        int n = scanner.nextInt();
        if (n <=0) {
            n = 10;
        }

        float suma = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("Introduce temperatura");
            suma += scanner.nextFloat();
        }

        float media = suma / n;

        System.out.printf("Media: %.2f",media);
    }

}
