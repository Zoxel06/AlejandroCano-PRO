import java.util.Scanner;

public class SimulacroExamen {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //ejercicio1();
        //ejercicio2();
        ejercicio3();
        //ejercicio4();
    }

    public static void ejercicio1() {
        int n = 0;
        int veces = 0;
        int sumatorioPares = 0, sumatorioImpares = 0;

        double mediaPares = 0;
        double mediaImpares = 0;
        int nPar = 0, nImpar = 0, nCero = 0;

        int opcion;

        System.out.println("Introduce los números que quieras operar, cuando introduzcas un numero negativo acabará la recopilación de los números");

        while (n > -1) {


            n = scanner.nextInt();
            veces++;
            if (n > -1) {

            }
            if (n % 2 == 0) {
                sumatorioPares += n;
                nPar++;
                mediaPares = (double) sumatorioPares / nPar;
            } else if (n % 2 != 0) {
                sumatorioImpares += n;
                nImpar++;
                mediaImpares = (double) sumatorioImpares / nImpar;
            }

            if (n == 0) {
                nCero++;

            }

        }

        do {


            System.out.println("Elige la opción que quieras realizar con los numeros introducidos\n");
            System.out.println("1-Suma de los números pares introducidos.\n" +
                    "2-Media de los números pares introducidos con dos decimales.\n" +
                    "3-Media de los números impares introducidos con dos decimales.\n" +
                    "4-Cuántos números hemos introducido.\n" +
                    "5-Cuantos números de los introducidos han sido ceros, cuántos han sido pares\n" +
                    "y cuantos impares.\n" +
                    "6-Salir.");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("La suma de los números pares introducidos es " + sumatorioPares);
                    break;
                case 2:
                    System.out.println("La media de los pares es " + sumatorioPares / nPar);
                    break;
                case 3:
                    System.out.println("La media de los números impares es " + sumatorioImpares / nImpar);
                    break;
                case 4:
                    System.out.println("Has introducido " + (nPar + nImpar));
                    break;
                case 5:
                    System.out.println("El número de pares es " + nPar);
                    System.out.println("El número de impares es " + nImpar);
                    System.out.println("El número de ceros es " + nCero);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Esta opción no se puede elegir, prueba con otra");
                    break;
            }


        } while (opcion != 6);
    }

    public static void ejercicio2() {

        int numeroSS = 0;
        double media = 0;
        int sumaNotas = 0;
        for (int i = 0; i < 20; i++) {
            System.out.println("Introduce nota:");
            int nota = scanner.nextInt();
            sumaNotas += nota;
            if (nota < 5) {
                numeroSS++;
            }
        }
        System.out.println("El numero de SS es de " + numeroSS);
        System.out.println("La media de la clase es de " + media);

    }

    public static void ejercicio3() {
        final int PIN = 1111; //el final no hace falta, es solo para que no se pueda cambiar
        int PINUsuario;
        int cantidad;
        //meter dinero
        //sacar dinero
        //consultar dinero
        //salir
        int opcion = 0;
        int saldo;

        do {
            System.out.println("1. Ingresar");
            System.out.println("2. Sacar");
            System.out.println("3. Consultar");
            System.out.println("4. Salir");

            opcion=scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Dime el PIN de la cuenta");
                    PINUsuario = scanner.nextInt();
                    if (PINUsuario==PIN){
                        System.out.println("¿Que cantidad quieres ingresar?");
                        cantidad = scanner.nextInt();
                        if (cantidad>0){
                            System.out.println("Saldo actualizado");
                        }else{
                            System.out.println("Cantidad inválida");
                        }
                    }else{
                        System.out.println("PIN incorrecto");
                    }
                    break;
                case 2:
                    System.out.println("Dime el PIN de la cuenta");
                    PINUsuario = scanner.nextInt();
                    if (PINUsuario==PIN){
                        System.out.println("¿Que cantidad quieres retirar?");
                        cantidad = scanner.nextInt();
                        if (cantidad>0&&cantidad>){
                            System.out.println("Saldo actualizado");
                        }else{
                            System.out.println("Cantidad inválida");
                        }
                    }else{
                        System.out.println("PIN incorrecto");
                    }
                    break;
                case 3:

                    break;
                case 4:
                    break;
            }
        } while (opcion != 4);
    }

    public static void ejercicio4(){
        /*a. Si su longitud es menor de 4, transformar todas sus letras en mayúsculas
        b. Si su longitud es mayor igual que 4 pero menor que 8, transformar sus letras en
                minúsculas
        c. Si su longitud es mayor igual que 8 pero menor que 11, todas las vocales deberán
        estar acentuadas
        d. Si la longitud es mayor igual a 11 se deberá dar la vuelta a la palabra*/

        String palabra = scanner.next();
        String palabraInversa;

        if (palabra.length()>=4 && palabra.length()<8){
            palabra = palabra.toUpperCase();
        }else if (palabra.length()<11){
            palabra = palabra.replaceAll("a","á")
                    .replaceAll("e","é")
                    .replaceAll("i","í")
                    .replaceAll("o","ó")
                    .replaceAll("u","ú");
        }else{
            palabraInversa = "";
            for (int i = palabra.length()-1; i >= 0; i--){
                palabraInversa += palabra.charAt(i);
            }
        }

        if (palabra.length()>=11){
            System.out.println(palabraInversa);

        }
    }
}
