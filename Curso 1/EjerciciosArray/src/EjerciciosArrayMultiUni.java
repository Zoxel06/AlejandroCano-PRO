import java.util.Arrays;
import java.util.Scanner;

public class EjerciciosArrayMultiUni {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //ejercicio1();
        //ejercicio2();
        //ejercicio3();
        //ejercicio4();
        //ejercicio5();
        //ejercicio6();
        //ejercicio7_1();
        //ejercicio7_2();
        //ejercicio8();
        ejercicio9();
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
            cuadrado[i] = (int) Math.pow(numeros[i], 2);
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

            if (mayor < numeros[i]) {
                mayor = numeros[i];
            }
            if (menor > numeros[i]) {
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


        int ultimo = numeros[numeros.length - 1];


        for (int i = 0; i < numeros.length - 1; i++) {
            numeros[i] = numeros[i + 1];
            System.out.println(numeros[i]);
        }


        numeros[0] = ultimo;

        System.out.print("\n");
        for (int i = 0; i < numeros.length; i++) {
            System.out.println(numeros[i]);
        }
    }


    public static void ejercicio5() {

        int[] numeros = new int[10];

        for (int i = 0; i < numeros.length - 1; i++) {
            numeros[i] = (int) (Math.random() * 10);
        }

        System.out.println("Array inicial:");
        for (int i = 0; i < numeros.length - 1; i++) {
            System.out.print(numeros[i] + ", ");
        }
        System.out.println();


        for (int i = 0; i < numeros.length - 1; i++) {
            if (numeros[i] % 2 == 0) {
                numeros[i] += 1;
            } else if (numeros[i] % 2 != 0) {
                numeros[i] -= 1;
            }
        }

        System.out.println("Array con incrementar en 1 los valores pares y en -1 los impares:");
        for (int i = 0; i < numeros.length - 1; i++) {
            System.out.print(numeros[i] + ", ");
        }
        System.out.println();

        for (int i = 0; i < numeros.length - 1; i++) {
            if (-1 < numeros[i] && numeros[i] < 5) {
                numeros[i] *= 2;
            }
        }

        System.out.println("Array con duplicar los valores positivos menores que 5");
        for (int i = 0; i < numeros.length - 1; i++) {
            System.out.print(numeros[i] + ", ");
        }
        System.out.println();


    }


    public static void ejercicio6() {

        int numeroMax = 0;

        System.out.println("Introduce la longitud del array");
        int longitud = scanner.nextInt();

        int[] numeros = new int[longitud];

        do {
            System.out.println("Introduce el numero maximo que quieres que tenga el array");
            numeroMax = scanner.nextInt();
            if (numeroMax < longitud) {
                System.out.println("El numero maximo no puede ser menor que la longitud del array");
            }
        } while (numeroMax < longitud);


        System.out.println("Ahora rellena el array");
        for (int i = 0; i < numeros.length - 1; i++) {
            numeros[i] = scanner.nextInt();
        }

        for (int i = 0; i < numeros.length - 1; i++) {
            System.out.print(numeros[i] + ", ");
        }

    }


    public static void ejercicio7_1() {

        char[] abecedario = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        System.out.println("Introduce la longitud de array");
        int longitud = scanner.nextInt();

        String[] palabras = new String[longitud];

    }


    public static void ejercicio7_2() {

    }


    public static void ejercicio8() {

    }


    public static void ejercicio9() {

        int opcion = 0;
        int[] numeros = null;

        do {
            System.out.println("1. Crear array");
            System.out.println("2. Rellenar aleatorios");
            System.out.println("3. Rellenar consola");
            System.out.println("4. Ordenar array");
            System.out.println("5. Clonar array");
            System.out.println("6. Mover izquierda");
            System.out.println("7. Mover derecha");
            System.out.println("8. Mover por pares");
            System.out.println("9. Invertir array");
            System.out.println("10. Imprimir array");
            System.out.println("11. Salir");

            System.out.println("Elige una opcion");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("¿Cuantas posiciones quieres reservar?");
                    int posiciones = scanner.nextInt();
                    numeros = new int[posiciones];
                    System.out.println("Arrau creado correctamente");
                    break;

                case 2:
                    if (numeros != null) {
                        System.out.println("Array rellenado con aleatorios");
                        for (int i = 0; i < numeros.length; i++) {
                            numeros[i] = (int) (Math.random() * 100);
                        }
                    } else {
                        System.out.println("Imposible, inicia el array antes");
                    }
                    break;

                case 3:
                    if (numeros != null) {
                        for (int i = 0; i < numeros.length; i++) {
                            System.out.println("Introduce el numero de la posicion " + i);
                            numeros[i] = scanner.nextInt();
                        }
                    } else {
                        System.out.println("Imposible, inicia el array antes");
                    }
                    break;

                case 4:
                    if (numeros != null) {
                        Arrays.sort(numeros);
                    } else {
                        System.out.println("Imposible, inicia el array antes");
                    }
                    break;

                case 5:
                    System.out.println("¿Cual es la nueva longitud del array?");
                    int nuevaLongitud = scanner.nextInt();
                    numeros = Arrays.copyOf(numeros, nuevaLongitud);
                    break;

                case 6:
                    if (numeros != null) {

                        int temporal = numeros[0];
                        for (int i = 0; i < numeros.length - 1; i++) {
                            numeros[i] = numeros[i + 1];
                        }
                        numeros[numeros.length - 1] = temporal;

                    } else {
                        System.out.println("Imposible, inicia el array antes");
                    }
                    break;

                case 7:
                    if (numeros != null) {

                        int temporal = numeros[numeros.length - 1];
                        for (int i = numeros.length - 1; i > 0; i--) {
                            numeros[i] = numeros[i - 1];
                        }
                        numeros[0] = temporal;

                    } else {
                        System.out.println("Imposible, inicia el array antes");
                    }
                    break;

                case 8:
                    if (numeros != null) {

                        for (int i = 0; i < numeros.length; i += 2) {
                            int temporal = numeros[i];
                            numeros[i] = numeros[i + 1];
                            numeros[i + 1] = temporal;
                        }
                    } else {
                        System.out.println("Imposible, inicia el array antes");
                    }
                    break;

                case 9:
                    if (numeros != null) {
                        for (int i = 0; i < numeros.length/2; i++) {
                            int temporal = numeros[i];
                            numeros[i] = numeros[numeros.length-1-i];
                            numeros[numeros.length-1-i] = temporal;
                        }
                    } else {
                        System.out.println("Imposible, inicia el array antes");
                    }
                    break;

                case 10:
                    if (numeros != null) {
                        for (int item : numeros) {
                            System.out.print(item + "\t");
                        }
                        System.out.println();
                    } else {
                        System.out.println("Imposible, inicia el array antes");
                    }
                    break;

                case 11:
                    break;
            }
        } while (opcion != 11);
    }

}
