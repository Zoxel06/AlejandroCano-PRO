import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Entrada {
    static Scanner scanner = new Scanner(System.in);
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {


        OperacionesFicheros operacionesFicheros = new OperacionesFicheros();

        // operacionesFicheros.leerInformacion();

        // operacionesFicheros.crearDirectorio("src/recursos/lectura/completa");

        // operacionesFicheros.crearFichero("src/recursos/lectura/ejemplo_lectura_codigo.txt");

        // operacionesFicheros.lecturaUnitaria("src/recursos/lectura/ejemplo.txt");

        // operacionesFicheros.lecturaCompleta("src/recursos/lectura/ejemplo.txt");

        // operacionesFicheros.lecturaCodigoUnitaria("src/recursos/lectura/ejemplo_lectura_codigo.txt");

         operacionesFicheros.lecturaCodigoCompleta("src/recursos/lectura/ejemplo_lectura_codigo.txt");

        operacionesFicheros.escribirFichero("src/recursos/lectura/escritura/completa/ejemplo_escritura.txt");


        System.out.println("Dime tu nombre");
        String nombre = scanner.next();
        System.out.println("Dime tu apellido");
        String apellido = scanner.next();
        System.out.println("Dime tu edad");
        int edad = scanner.nextInt();
        System.out.println("Dime el mensaje que quieras");
        String mensaje = null;
        try {
            mensaje = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Error en la lectura");
        }
        operacionesFicheros.escribirDatos("src/recursos/lectura/escritura/completa/datos.txt",nombre,apellido,edad,mensaje);

    }

}
