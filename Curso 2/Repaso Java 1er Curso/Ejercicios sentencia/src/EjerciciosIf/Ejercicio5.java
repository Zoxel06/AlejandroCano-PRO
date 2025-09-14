package EjerciciosIf;

import java.util.Scanner;

public class Ejercicio5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce un número entero");

        int n = scanner.nextInt();
        int x = 0;

        if (n%2==0) {
            x = n + 2;
        }

        if (n%3==0) {
            x+=3;
        }

        if (n%5==0) {
            x+=5;
        }

        if (n%2!= 0 && n%3!=0 && n%5!=0) {
            x = n+=1;
        }

        System.out.println(x);

    }

}
