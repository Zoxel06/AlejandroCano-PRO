package ejercicio2;

import java.util.Scanner;

public class Entrada {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("¿Cuantos alumnos hay en la clase?");
        int cantidad = scanner.nextInt();


            for (int i = 0; i < cantidad; i++) {
                System.out.println("Escribe nombre del alumno");
            }

    }
}
