import java.util.Scanner;

public class EjerciciosArrayMultiUni {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //ejercicio1();
        //ejercicio2();
        //ejercicio3();
        ejercicio4();
        //ejercicio5();
        //ejercicio6();
        //ejercicio7();
        //ejercicio8();
        //ejercicio9();
    }


    public static void ejercicio1() {

        int[] numeros = new int[5];
        int[] cuadrado = new int[numeros.length];
        int sumaCuadrados = 0;
        double media = 0;

        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Introduce numero");
            numeros[i] = scanner.nextInt();
        }

        for (int i = 0; i < numeros.length; i++) {
            cuadrado[i] = (int) Math.pow(numeros[i],2);
            System.out.print(cuadrado[i] + " ");
            sumaCuadrados += cuadrado[i];
        }
        System.out.println();

        media = (double) (sumaCuadrados / numeros.length);
        System.out.println("La suma de todos los números es :" + sumaCuadrados);
        System.out.println("La media de todos los números es: " + media);

    }


    public static void ejercicio2() {

        int longitud = 0;

        System.out.println("Introduce la longitud que quieres que tenga el Array");
        longitud = scanner.nextInt();

        int[] numeros = new int[longitud];

        int mayor = numeros[0];
        int menor = numeros[0];

        System.out.println("Dime los datos del array");

        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = scanner.nextInt();

            if (mayor<numeros[i]){
                mayor = numeros[i];
            }
            if (menor>numeros[i]) {
                menor = numeros[i];
            }
        }
        System.out.println("El numero mayor es: " + mayor);
        System.out.println("El numero menor es: " + menor);
    }


    public static void ejercicio3() {

        int longitud = 0;

        System.out.println("Introduce la longitud que quieres que tengan los Arrays");
        longitud = scanner.nextInt();

        int[] numeros1 = new int[longitud];
        int[] numeros2 = new int[longitud];
        int[] numeros3 = new int[longitud];

        System.out.println("Introduce los datos del primer Array");
        for (int i = 0; i < numeros1.length; i++) {
            numeros1[i] = scanner.nextInt();
        }

        System.out.println("Introduce los datos del primer Array");
        for (int i = 0; i < numeros1.length; i++) {
            numeros2[i] = scanner.nextInt();
        }

        System.out.println("El tercer array es: ");

        for (int i = 0; i < numeros1.length; i++) {
            numeros3[i] = numeros1[i] + numeros2[i];
            System.out.println(numeros3[i]);
        }

    }


    public static void ejercicio4() {

        int longitud = 0;

        System.out.println("Dime la longitud del array");
        longitud = scanner.nextInt();

        int[] numeros = new int[longitud];

        System.out.println("Introduce los datos del array");
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = scanner.nextInt();
        }
    }


    public static void ejercicio5() {

    }


    public static void ejercicio6() {

    }


    public static void ejercicio7() {

    }


    public static void ejercicio8() {

    }


    public static void ejercicio9() {

    }

}
