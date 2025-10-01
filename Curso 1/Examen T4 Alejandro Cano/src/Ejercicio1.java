import java.util.Arrays;

public class Ejercicio1 {

    public static void main(String[] args){

        int[] numeros = new int[15];


        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = (int) (Math.random()*17);
        }

        System.out.println("Elementos del array sin ordenar:");
        for ( int item : numeros ) {
            System.out.print(item + "\t");
        }
        System.out.println();

        System.out.println("Elementos del array ordenados:");
        for (int i = 0; i < numeros.length; i++) {
            Arrays.sort(numeros);
            System.out.print(numeros[i] + "\t");
        }
        System.out.println();

        System.out.println("Numeros en posicion par:");
        for (int i = 0; i < numeros.length; i+=2) {
                System.out.print(numeros[i] + "\t");
        }
        System.out.println();

        System.out.println("Numeros pares:");
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] % 2 == 0){
                System.out.print(numeros[i] + "\t");
            }
        }
    }
}
