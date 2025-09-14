package EjerciciosSwitch;

import java.util.Scanner;

public class Ejercicio4 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Dime un mes del año en número");
        int mes = scanner.nextInt();

        switch (mes) {
            case 12,1,2:
                System.out.println("La estación es invierno");
            case 3,4,5:
                System.out.println("La estación es primavera");
            case 6,7,8:
                System.out.println("La estación es verano");
            case 9,10,11:
                System.out.println("La estación es otoño");
        }

    }

}
