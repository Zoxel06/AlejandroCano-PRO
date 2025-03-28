import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Entrada {
    static Scanner scanner = new Scanner(System.in);
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        OperacionesFicheros operacionesFicheros = new OperacionesFicheros();

        /*
        System.out.println("Dime el mensaje que quieras cifrar");
        String mensaje = null;
        try{
            mensaje = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Error en la lectura del mensaje");
        }
        System.out.println("Indica cual es la fase de cifrado");
        int faseCifrado = scanner.nextInt();

        operacionesFicheros.cifrarMensaje("src/recursos/mensaje.txt",mensaje,faseCifrado);

        System.out.println("Indica cual es la fase de descifrado");
        int faseDescifrado = scanner.nextInt();

        operacionesFicheros.descifrarCodigo("src/recursos/mensaje.txt",mensaje,faseDescifrado);
         */

        operacionesFicheros.escrituraFicheroCompleto("src/recursos/escrituraCompleta");

    }


}
