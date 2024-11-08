import java.util.Scanner;


public class EjerciciosArray {


    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {


        //ejercicio1();
        //ejercicio2();
        //ejercicio3();
        //ejercicio4();
        //ejercicio5();
        //ejercicio6();
        ejercicio7();
    }


    public static void ejercicio1() {


        int[] numeros = new int[10];


        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Introduce el numero");
            numeros[i] = scanner.nextInt();
        }


        for (int i = 0; i < numeros.length; i++) {
            System.out.printf("El numero en la posicion %d es %d\n", i + 1, numeros[i]);
        }
    }


    public static void ejercicio2() {

        int[] randoms = new int[30];
        int sumaTotal = 0;


        for (int i = 0; i < randoms.length; i++) {
            randoms[i] = (int) (Math.random() * 11);
            System.out.println(randoms[i]);
            sumaTotal += randoms[i];
        }

        double media = (double) sumaTotal / randoms.length;

        System.out.println("La suma de los puntos es " + sumaTotal);
        System.out.println("La media de los puntos es " + media);
    }


    public static void ejercicio3() {

        int[] numeros = new int[10];


        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = scanner.nextInt();
        }


        System.out.println("Los numeros en orden reverso son :");
        for (int i = numeros.length - 1; i >= 0; i--) {
            System.out.println(numeros[i]);
        }


        System.out.println("Los numeros en orden normal son :");
        for (int i = 0; i < numeros.length; i++) {
            System.out.println(numeros[i]);
        }


    }


    public static void ejercicio4() {


        int[] numero = new int[20];
        int[] cuadrado = new int[numero.length]; // pongo numero.lenght en vez de 20 por si quiero cambiar el numero de
        int[] cubo = new int[numero.length];     //  posiciones en "numero" y que se cambien en el resto


        for (int i = 0; i < numero.length; i++) {
            numero[i] = (int) (Math.random() * 101);
            System.out.println("Numero: " + numero[i]);
            System.out.println("Cuadrado: " + (int) Math.pow(numero[i], 2));
            System.out.println("Cubo: " + (int) Math.pow(numero[i], 3));
        }
    }


    public static void ejercicio5() {


        int[] numeros = new int[8];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = scanner.nextInt();
        }


        for (int i = 0; i < numeros.length; i++) {

            if (numeros[i] % 2 == 0) {
                System.out.println("Par: " + numeros[i]);
            } else {
                System.out.println("Impar: " + numeros[i]);
            }
        }
    }


    public static void ejercicio6() {


        int[] numeros = new int[20];
        int modificaciones = 0;


        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = (int) (Math.random() * 31);
            System.out.print(numeros[i] + " ");
        }
        System.out.println();


        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == 6) {
                numeros[i] = 8;
                modificaciones++;
            }


            if (numeros[i] == 7) {
                numeros[i] = 15;
                modificaciones++;
            }


            if (numeros[i] == 20) {
                numeros[i] = 10;
                modificaciones++;
            }


            System.out.print(numeros[i] + " ");
        }
        System.out.println();
        System.out.println("Numero de modificaciones: " + (modificaciones));
    }


    public static void ejercicio7() {   // FALTA (VER EN GITHUB DE CLASE)


        String[] palabras = new String[10];
        int opcion = 0;
        int numeroLetras = 0;
        int mediaLetras = 0;
        int longitudPalabraMayor = 0;
        int longitudPalabraMenor = 100;
        String palabraMayor = "";
        String palabraMenor = "";

        for (int i = 0; i < palabras.length; i++) {
            System.out.println("Introduce palabra");
            palabras[i] = scanner.next();
            if (palabras[i].length() > longitudPalabraMayor) {
                palabraMayor = palabras[i];
            }

        }


        do {
            System.out.println("1. Ver todas las palabras");
            System.out.println("2. Obtener una palabra al azar");
            System.out.println("3. Ver número de letras en total");
            System.out.println("4. Ver media de letras");
            System.out.println("5. Ver palabra con más letras");
            System.out.println("6. Ver palabra con menos letras");
            System.out.println("7. Salir");


            opcion = scanner.nextInt();


            switch (opcion) {
                case 1:
                    for (int i = 0; i < palabras.length; i++) {
                        System.out.println(palabras[i]);
                        break;
                    }
                    break;
                case 2:
                    String aleatoria = palabras[(int) (Math.random() * palabras.length)];
                    System.out.println(aleatoria);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Esta opción no está entre las permitidas");
            }
        } while (opcion != 7);
    }


}

