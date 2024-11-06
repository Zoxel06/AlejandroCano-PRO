import java.util.Scanner;

public class Ejercicio3 {


    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        int clave = 1898;
        int claveUsuario = 0;
        int intentos = 4;


        do {
            System.out.println("Introduce la clave de apertura de 4 digitos");
            claveUsuario = scanner.nextInt();

            if (clave != claveUsuario) {
                System.out.println("Intento fallido, prueba de nuevo");
                intentos -= 1;
            } else {
                System.out.println("Perfecto, la caja ha sido abierta");
                break;
            }

            if (intentos==0){
                System.out.println("Lo siento, caja bloqueada");
            }
        } while (intentos > 0);

    }
}
