import java.util.Scanner;
public class Ejercicio3 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Introduce el tipo de cafe que quieres");
        String cafe = scanner.next();
        System.out.println("Introduce la cantidad de azucar que quieres");
        int azucar = scanner.nextInt();
        System.out.println("Introduce el tamaño");
        String tamaño = scanner.next();
        System.out.printf("Preparando un %s de tamaño %s con %d cucharaditas de azucar",cafe,tamaño,azucar);
    }
}
