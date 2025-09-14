package EjerciciosIf;

import java.util.Scanner;

public class Ejercicio4 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce un número entero");

        int n = scanner.nextInt();

        if (n%2 == 0){
            n+=1;
            System.out.println(n);
        } else {
            n-=1;
            System.out.println(n);
        }

    }
}
