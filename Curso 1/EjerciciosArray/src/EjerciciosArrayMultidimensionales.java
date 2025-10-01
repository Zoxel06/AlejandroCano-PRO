import java.util.Scanner;

public class EjerciciosArrayMultidimensionales {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int modificacion = 0;

        // definir un array con filas 5 y columnas aleatorias entre 2-6
        // rellenar el array (entero) con aleatorios

        int[][] numeros = new int[5][(int) (Math.random() * 5 + 2)];

        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros[i].length; j++) {
                numeros[i][j] = (int) (Math.random() * 99 + 1);
            }
        }


        imprimirArray(numeros);

        System.out.println();

        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros[i].length; j++) {
                numeros[i][j] -= 1;
            }
        }

        System.out.println("Array - 1");
        imprimirArray(numeros);

        System.out.println();

        System.out.println("Array modificado");
        modificarArray(numeros);
    }

    public static void imprimirArray(int[][] array) {
        for (int[] fila : array) {
            for (int element : fila) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
    }

    public static void modificarArray(int[][] array){
        for (int[] fila : array) {
            for (int element : fila) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
    }

}
