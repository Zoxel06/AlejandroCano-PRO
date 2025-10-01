import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio3 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int[] numeros = new int[20];
        int comprobacion = 0;
        int opcion = 0;
        int pasarCaso1 = 0;

        do {
            System.out.println("1. Registrar posiciones");
            System.out.println("2. Obtener elementos de una posicion");
            System.out.println("3. Mostrar el array completo");
            System.out.println("4. Mostrar el array ordenado");
            System.out.println("5. Rotar el array");
            System.out.println("6. Salir");
            System.out.println("Seleccione una opcion");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    for (int i = 0; i < numeros.length; i++) {
                        System.out.println("Introduce el numero en la posicion " + i);
                        numeros[i] = scanner.nextInt();
                    }
                    System.out.println("Array registrado correctamente");
                    pasarCaso1 = 1;
                    break;

                case 2:
                    if (pasarCaso1 != 0) {
                        System.out.println("Introduce la posicion que quieras comprobar");
                        comprobacion = scanner.nextInt();
                        if (comprobacion < 20) {
                            System.out.println(numeros[comprobacion]);
                        } else {
                            System.out.println("Posicion inexistente, introduzca una posicion entre 0 y 19");
                        }
                    } else {
                        System.out.println("No se ha registrado ningun array");
                    }
                    break;

                case 3:
                    if (pasarCaso1 != 0) {
                        System.out.println("Array original completo:");
                        for (int i = 0; i < numeros.length; i++) {
                            System.out.print(numeros[i] + "\t");
                        }
                        System.out.println();
                    } else {
                        System.out.println("No se ha registrado ningun array");
                    }
                    break;

                case 4:
                    if (pasarCaso1 != 0) {
                        Arrays.sort(numeros);
                        for (int i = 0; i < numeros.length; i++) {
                            System.out.print(numeros[i] + "\t");
                        }
                        System.out.println();
                    } else {
                        System.out.println("No se ha registrado ningun array");
                    }
                    break;

                case 5:
                    if (pasarCaso1 != 0) {
                        System.out.println("Rotando posiciones a la izquierda...");

                        int temporal = numeros[0];
                        for (int i = 0; i < numeros.length - 1; i++) {
                            numeros[i] = numeros[i + 1];
                        }
                        numeros[numeros.length - 1] = temporal;

                        for (int i = 0; i < numeros.length; i++) {
                            System.out.print(numeros[i] + "\t");
                        }
                        System.out.println();
                    } else {
                        System.out.println("No se ha registrado ningun array");
                    }
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion no valida, introduzca una opcion existente");
                    break;
            }

        } while (opcion != 6);
    }
}
