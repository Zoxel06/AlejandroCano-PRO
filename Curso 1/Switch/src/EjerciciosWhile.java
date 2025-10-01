import java.util.Scanner;

public class EjerciciosWhile {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //ejercicioClaseWhile();
        //ejercicioClaseDoWhile();
        //ejercicio7();
        //ejercicio1();
        ejercicio2();
        //ejercicio3();
        //ejercicio4();
        //ejercicio5();
        //ejercicio6();
        //ejercicio8();
        //ejercicio9();


    }

    public static void ejercicioClaseWhile() {
        //PEDIR POR CONSOLA NUMEROS HASTA QUE EL USUARIO INTRODUZCA UN 0
        System.out.println("Adivina el numero que estoy pensando");
        int n = scanner.nextInt();
        int i = 0;
        while (n != i) {
            System.out.println("Ese no es el numero que estoy pensando");
            System.out.println("Introduce otro numero distinto");
            n = scanner.nextInt();

        }
        System.out.println("Muy bien, ese era el numero que estaba pensando");
    }

    public static void ejercicioClaseDoWhile() {
        int numero;
        int i = 0;
        do {
            System.out.println("Introduce un numero");
            numero = scanner.nextInt();
            i++;
        } while (numero != 0);
        System.out.println("Muy bien, has acertado el numero");
        System.out.println("Has necesitado " + i + " intentos");
    }

    public static void ejercicio7() {
        int opcion;
        do {
            System.out.println("1.Opcion añadir\n2.Opcion borrar\n3.Opcion listar\n4.Opcion buscar\n5.Salir\n¿Que quieres hacer?:");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Añadir");
                    break;
                case 2:
                    System.out.println("Borrar");
                    break;
                case 3:
                    System.out.println("Listar");
                    break;
                case 4:
                    System.out.println("Bbuscar");
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Esa opcion no esta entre las mostradas");
            }
        } while (opcion != 5);

        System.out.println("Terminando programa");
    }

    public static void ejercicio1() {
        int numero;
        int sumaPositivos = 0;
        int intentos = 0;
        do {
            System.out.println("Introduce un numero");
            numero = scanner.nextInt();
            intentos++;
            if (numero > 0) {
                sumaPositivos += numero;

            }
        } while (numero != 0);
        System.out.println("La suma de los positivos es " + sumaPositivos);
        System.out.println("Los intentos introducidos son " + intentos);
    }

    public static void ejercicio2() {
        System.out.println("Introduce el primer numero entre 0 y 100");
        int n1 = scanner.nextInt();
        System.out.println("Introduce el segundo numero entre 0 y 100");
        int n2 = scanner.nextInt();
        do {

        } while ();
    }

    public static void ejercicio3() {
        int aleatorio, numeroMax = -1, numeroMin = 101;
        do {
            aleatorio = (int) (Math.random() * 101);
            System.out.println(aleatorio);
            if (aleatorio > numeroMax) {
                numeroMax = aleatorio;
            } else if (aleatorio < numeroMin) {
                numeroMin = aleatorio;
            }
        } while (aleatorio != 0);
        System.out.println("El numero mas grande es " + numeroMax);
        System.out.println("El numero mas pequeño es " + numeroMin);
    }

    public static void ejercicio4() {

    }

    public static void ejercicio5() {
        int numeroGenerado = (int) (Math.random() * 21);
        int numeroUsuario = 0;
        int intentosUsuario = 0;
        int numeroIntentos = 5;
        System.out.println(numeroGenerado);
        do {

            System.out.println("Por favor introduce un numero");
            numeroUsuario = scanner.nextInt();
            intentosUsuario++;
            numeroIntentos--;

            if (numeroGenerado == numeroUsuario) {
                System.out.println("Enhorabuena, lo has acertado");
            } else {
                System.out.println("Lo siento, has fallado");
            }
        } while (numeroGenerado != numeroUsuario && numeroIntentos > 0);
    }

    public static void ejercicio6() {
        int numeroGenerado = (int) (Math.random() * 21);
        int numeroUsuario = 0;
        int intentosUsuario = 0;
        int numeroIntentos = 10;
        int record = 11;
        System.out.println(numeroGenerado);
        String repetir;
        do {

        do {

            System.out.println("Por favor introduce un numero");
            numeroUsuario = scanner.nextInt();
            intentosUsuario++;
            numeroIntentos--;

            if (numeroGenerado == numeroUsuario) {
                System.out.println("Enhorabuena, lo has acertado");
            } else {
                System.out.println("Lo siento, has fallado");
            }
        } while (numeroGenerado != numeroUsuario && numeroIntentos > 0);
        System.out.println("¿Quieres volver a jugar?");
        repetir = scanner.next();
        } while (repetir.equalsIgnoreCase("s"));

    }


}
