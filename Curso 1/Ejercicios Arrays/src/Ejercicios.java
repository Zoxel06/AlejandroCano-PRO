import java.util.Scanner;

public class Ejercicios {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        //ejercicio1();
        ejercicio2();
        //ejercicio3();
        //ejercicio4();
        //ejercicio5();
        //ejercicio6();
        //ejercicio7();
    }

    public static void ejercicio1() {

        int[] numeros = new int[10];

        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Introduce el numero");
            numeros[i] = scanner.nextInt();
        }

        for (int i = 0; i < numeros.length; i++) {
            System.out.printf("El numero en la posicion %d es %d\n", i + 1, numeros[i]);
        }
    }

    public static void ejercicio2() {

    }

    public static void ejercicio3() {

    }

    public static void ejercicio4() {

    }

    public static void ejercicio5() {

    }

    public static void ejercicio6() {

    }

    public static void ejercicio7() {

    }
}
