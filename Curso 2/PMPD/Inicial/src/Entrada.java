import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Primer programa de repaso");
        // variable -> guardar valor que será utilizado
        final String DNI = "123123A";
        System.out.println("Introduce tu nombre");
        String nombre = scanner.next();
        String correo; // null
        System.out.println("Introduce tu edad");
        int edad = scanner.nextInt();
        // Array -> lista no dinámica -> final
        // ArrayList / HashTable -> lista dinámica -> final

        System.out.printf("Mi nombre es %s y tengo %d años\n",nombre,edad);

        

    }

}
