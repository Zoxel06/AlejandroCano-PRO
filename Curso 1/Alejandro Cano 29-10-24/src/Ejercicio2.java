import java.util.Scanner;

public class Ejercicio2 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int numeroUsuario = 0;
        int numeroMax = 0, numeroMin = 100;
        int random = 0;
        int acumuladorRandoms = 0;
        boolean coincide = false;

        System.out.println("Introduce un numero del 1 al 100");
        numeroUsuario = scanner.nextInt();

        for (int i = 0; i < 10; i++) {
            random = (int) (Math.random() * 100 + 1);
            System.out.println(random);
            acumuladorRandoms += random;

            if (random > numeroMax){
                numeroMax = random;
            }

            if (random < numeroMin){
                numeroMin = random;
            }

            if (numeroUsuario==random){
                coincide = true;
            }

        }

        System.out.println("El numero maximo de los generados es "+numeroMax);

        System.out.println("El numero minimo de los generados es "+numeroMin);

        System.out.println("El numero medio es " + (double) (acumuladorRandoms / 10));

        System.out.println("La suma de todos los numeros es " + acumuladorRandoms);

        System.out.println("El número introducido es alguno de los generados es: "+coincide);


    }
}
