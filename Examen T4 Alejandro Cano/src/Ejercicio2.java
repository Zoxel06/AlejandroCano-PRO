import java.util.Scanner;

public class Ejercicio2 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        int mayor = 0, menor = 21;
        int sumatorioPares = 0, sumatorioImpares = 0, sumatorioRepetido = 0;
        int numeroRepetido = 0;

        System.out.println("Introduce la cantidad de datos que quieres que tenga el array");
        int longitud = scanner.nextInt();

        int[] numeros = new int[longitud];

        System.out.println("Array original:");
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = (int) (Math.random()*21);
            System.out.print(numeros[i] + "\t");
        }
        System.out.println("\n");

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] > mayor){
                mayor = numeros[i];
            }

            if (numeros[i] < menor){
                menor = numeros[i];
            }
        }

        System.out.println("El numero mayor es " + mayor);
        System.out.println("El numero menor es " + menor);

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] % 2 == 0){
                sumatorioPares++;
            }
            if (numeros[i] % 2 != 0){
                sumatorioImpares++;
            }
        }

        System.out.println("Cantidad de pares: " + sumatorioPares);
        System.out.println("Cantidad de impares: " + sumatorioImpares);
        System.out.println();

        System.out.println("Introduce un numero para comprobar cuantas veces aparece en la lista");
        numeroRepetido = scanner.nextInt();
        for (int i = 0; i < numeros.length; i++) {
            if (numeroRepetido == numeros[i]){
                sumatorioRepetido++;
            }
        }

        System.out.println("El " + numeroRepetido + " aparece un total de " + sumatorioRepetido + " veces en la lista");
    }
}
