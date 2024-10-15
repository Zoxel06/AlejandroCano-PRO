import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class EjerciciosFor {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        //ejercicio1();
        //ejercicio2();
        //ejercicio3();
        //ejercicio4();
        //ejercicio5();
        //ejercicio6();
        //ejercicio7();
        //ejercicio8();
        //ejercicio9();
        //ejercicio10();
        //ejercicio11();
        //ejercicio12();
        //ejercicio13();
        //ejercicio14();
        //ejercicio14extendido();
        //ejercicio15();
        //ejercicio16();
        //ejercicio17();
        //ejercicioRandom();
    }


    public static void ejercicio1(){
        System.out.println("¿Cuantas temperaturas quieres introducir?");
        int nTemperaturas = scanner.nextInt();
        int temperatura = 0; //le puedo poner el valor que quiera ya que luego lo voy a cambiar, esto es solo para que se quede registrada la variable
        int sumatorio = 0; //lo mismo que arriba
        float media = 0;

        if (nTemperaturas<=0){
            nTemperaturas=10;
        }

        for(int i=0; i<nTemperaturas; i++){
            System.out.println("Introduce temperatura");
            temperatura = scanner.nextInt();
            sumatorio += temperatura;
        }
        media = (float)sumatorio/nTemperaturas;
        System.out.println("La suma de todas las temperaturas es: "+sumatorio);
        System.out.println("La media de todas las temperaturas es: "+media);
    }


    public static void ejercicio2(){

        System.out.println("Dime de que numero del 1 al 10 quieres ver su tabla de multiplicar");
        int n = scanner.nextInt();

        for(int i=0;i<11;i++){
            int r = n * i;
            System.out.println(n+" * "+i+" = "+r);
        }
    }

    public static void ejercicio3(){

        System.out.println("Dime hasta que tabla quieres que calcule");
        int n = scanner.nextInt();

        for (int i = 0; i <=n ; i++) {
            System.out.println("Tabla de multiplicar del "+i);
            for(int j=0; j<11; j++){
                System.out.printf("\t%d * %d = %d\n",i,j,i*j);
            }
        }
    }

    public static void ejercicio4(){

        System.out.println("Dime hasta que tabla quieres que calcule");
        int nInicial = scanner.nextInt();
        System.out.println("Dime hasta que tabla quieres que calcule");
        int nFinal = scanner.nextInt();

        if (nInicial >= nFinal){
            System.out.println("No se puede");
        }
        else{
        for (int i = nInicial; i <=nFinal ; i++) {
            System.out.println("Tabla de multiplicar del "+i);
            for(int j=0; j<11; j++){
                System.out.printf("\t%d * %d = %d\n",i,j,i*j);
            }
        }
        }
    }

    public static void ejercicio5(){
        System.out.println("Dime la base");
        int base = scanner.nextInt();
        System.out.println("Dime el exponente");
        int exponente = scanner.nextInt();
        int resultado = 1;

        if (exponente ==0) {
            System.out.println("El resultado de la potencia es 1");
        }else if (base==0) {
            System.out.println("No se calculan potencias con base 0");
        } else {
            for (int i = 0; i < exponente; i++){
                resultado = resultado * base;
            }
            System.out.println("El resultado de la potencia es "+resultado);
        }
    }

    public static void ejercicio6(){
        System.out.println("Di desde que numero quieres calcular");
        int numeroInicial = scanner.nextInt();
        System.out.println("Di hasta que numero quieres calcular");
        int numeroFinal =scanner.nextInt();
        for (int i = numeroInicial; i <= numeroFinal; i++){

            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }

    public static void ejercicio7(){
        System.out.println("¿Cuantas veces quieres tirar el dado?");
        int numeroVeces = scanner.nextInt();
        if (numeroVeces<1){
            System.out.println("No se pueden tirar veces negativas");
            numeroVeces=100;
        }
        for (int i = 1; i <= numeroVeces; i++){
            // i = 1 en vez de 0 para que salga 1 como primera tirada, por lo que a su vez el numeroVeces tiene que ser <=
            int tirada = (int)(Math.random()*6)+1;
            System.out.printf("La tirada numero %d es %d\n",i,tirada);
        }
    }

    public static void ejercicio8(){
        int acumuladorPositivos=0, acumuladorNegativos=0, numeroCeros=0
                , numeroPositivos=0, numeroNegativos=0;
        //si las variables son del mismo tipo (int en este caso) se pueden escribir en la misma linea
        for (int i = 0; i < 10; i++){
            System.out.println("Por favor introduce el numero que quieres");
            int numero = scanner.nextInt();
            if (numero<0){
                acumuladorNegativos +=numero;
                numeroNegativos++;
            }else if (numero>0){
                acumuladorPositivos +=numero;
                numeroPositivos++;
            }else{
                numeroCeros++;
            }
        }

        System.out.println("La media de positivos es "+((float)acumuladorPositivos/numeroPositivos));
        System.out.println("La media de negativos es "+((float)acumuladorNegativos/numeroNegativos));
        System.out.println("La media de ceros es "+((float)numeroCeros));
    }

    public static void ejercicio9(){
        int sueldosMayores = 0, sumatorioSueldos = 0;
        for (int i= 0; i <10;i++){
            System.out.println("Introduce el sueldo");
            int sueldo = scanner.nextInt();
            if (sueldo>1000){
                sueldosMayores++;
            }
            sumatorioSueldos+=sueldo;
        }
        System.out.println("");
    }

    public static void ejercicio10(){
        int numeroSS=0,numeroAP=0,numeroCD=0;
        for (int i = 0;i < 6;i++){
            System.out.println("Introduce una nota");
            int nota = scanner.nextInt();
            if (nota>=0 && nota <=10){
                if (nota > 4) {
                    numeroAP++;
                }else if(nota<4)  {
                    numeroSS++;
                }else{
                    numeroCD++;
                }
            }else{
                System.out.println("Nota invalida");
            }
        }
        System.out.println("El numero de AP es "+numeroAP);
        System.out.println("El numero de SS es "+numeroSS);
        System.out.println("El numero de CD es "+numeroCD);
    }

    public static void ejercicio11(){
        System.out.println("De que lado quieres el cuadrado");
        int lado = scanner.nextInt();
        for (int i=0;i<lado;i++) {
            //System.out.println("\t+\t");
            for (int j = 0; j < lado; j++) {
                //primera o ultima fila
                if (i == 0 || i == lado-1)  {
                System.out.print("\t+\t");
            } else if (j == 0 || j==lado-1) {
                    System.out.print("\t+\t");
                }
                else{
                    System.out.print("\t \t");
                }
            }
            System.out.println();
        }
    }

    public static void ejercicioRandom(){
        System.out.println("Dime una frase");
        String frase = scanner.nextLine();
        frase = frase.replaceAll(" ", "");

        for (int i = 0;i<frase.length();i++){
            char letra = frase.charAt(i);
            System.out.println(letra);
        }
    }

    public static void ejercicio13(){
        System.out.println("Dime una palabra");
        String palabra = scanner.next();

        for (int i = palabra.length()-1; i >= 0; i--){
            System.out.println(palabra.charAt(i));
        }
    }

    public static void ejercicio14(){
        System.out.println("Dime que palabra quieres comprobar si es palindromo");
        String palabra = scanner.next();

        palabra= palabra.toLowerCase().replaceAll("á","a")
                .replaceAll("é","e")
                .replaceAll("í","i")
                .replaceAll("ó","o")
                .replaceAll("ú","u");
        String palabraInversa = "";


        for (int i = palabra.length()-1; i >= 0; i--){
            palabraInversa+=palabra.charAt(i);
        }

        if (palabra.equals(palabraInversa)){
            System.out.println("Es palindromo");
        }else{
            System.out.println("No es palindromo");
        }

    }

    public static void ejercicio14extendido(){

    }

    public static void ejercicio15(){

    }

    public static void ejercicio16(){

    }

    public static void ejercicio17(){

    }
}
