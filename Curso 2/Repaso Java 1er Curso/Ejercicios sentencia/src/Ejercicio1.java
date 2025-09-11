import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el primer numero");
        int entero1 = scanner.nextInt();
        System.out.println("Introduce el segundo numero");
        int entero2 = scanner.nextInt();

        if (entero1 > entero2){
            System.out.println("El primero es mayor que el segundo");
        } else if (entero2 > entero1) {
            System.out.println("El segundo es mayor que el segundo");
        } else {
            System.out.println("Son iguales");
        }

    }

}
