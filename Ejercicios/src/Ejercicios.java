import java.util.Scanner;


public class Ejercicios {

    static Scanner lectorTeclado = new Scanner(System.in);

    public static void main(String[] args) {
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
        //ejercicio15();

    }
    public static void ejercicio1(){

        final String NOMBRE = "Ivan Grinchuk";
        final String CALLE = "C/Maria Martinez";
        final int PORTAL = 9;
        final String LETERA = "A";
        final int CODIGO_POSTAL = 28025;
        final String PAIS = "Espana";
        final int PISO = 3;
        final String CUIDAD = "Madrid";


        System.out.println(NOMBRE);
        System.out.printf("%s n %d,%d%s\n",CALLE,PORTAL,PISO,LETERA);
        System.out.printf("%d %s\n",CODIGO_POSTAL,CUIDAD);
        System.out.println(PAIS);

    }

    public static void ejercicio2(){

        String Nombre; //null
        Integer Edad;

        System.out.println("ESCRIBE TU NOMBRE COMPLETO: ");
        Nombre = lectorTeclado.nextLine();

        System.out.println("ESCRIBE TU EDAD: ");
        Edad = lectorTeclado.nextInt();

        System.out.printf("Te llamas %s\n",Nombre);
        System.out.printf("Tienes %d anos\n",Edad);
        System.out.printf("Pulsa enter para continuar...");

    }

    public static  void ejercicio3(){

        Integer n1;
        Integer n2;

        System.out.println("Primer numero");
        n1 = lectorTeclado.nextInt();

        System.out.println("Segundo numero");
        n2 = lectorTeclado.nextInt();

        int Suma = n1 + n2;
        int Resta = n1 - n2;
        int Multiplicacion = n1 * n2;
        int Divicion = n1 / n2;
        int Resto = n1 % n2;
        float Divicion_real = (float)n1 / (float)n2;
        float Resto_real = (float)n1 - (float)n2;

        System.out.println("Suma: " +Suma);
        System.out.println("Resta: " +Resta);
        System.out.println("Multiplicacion : " +Multiplicacion);
        System.out.println("Divicion: " +Divicion);
        System.out.println("Resto: " +Resto);
        System.out.println("Divicion_real: " +Divicion_real);
        System.out.println("Resto_real: " +Resto_real);

    }

    public static void ejercicio4(){

        int bebida;
        int bocadillos;

        System.out.println("Cuantos bebidas has comprado?");
        bebida = lectorTeclado.nextInt();

        System.out.println("Cuantos bocadillos has comprado?");
        bocadillos = lectorTeclado.nextInt();

        double precio_bebida = 1.25;
        double precio_bocadillos = 2.05;

        float coste_bebida = (float) bebida * (float) precio_bebida;
        float coste_bocadillo = (float) bocadillos * (float) precio_bocadillos;

        float total_precio = coste_bebida + coste_bocadillo;

        System.out.println("Numero de bebidas: " +bebida);
        System.out.println("Numero de bocadillos: " +bocadillos);
        System.out.printf("Coste de la bebida: %.2f \n",coste_bebida);
        System.out.printf("Coste de bocadillos: %.2f \n",coste_bocadillo);
        System.out.printf("Coste consumición: %.2f \n",total_precio);

    }

    public static void ejercicio5(){

        int total_segundos = 24973;
        int horas = total_segundos / 3600;
        int segundos = total_segundos % 3600;
        int minutos = segundos / 60;
        segundos = segundos % 60;

        System.out.printf("Numero de segundos: %d\n",total_segundos);
        System.out.printf("Horas: %d\n",horas);
        System.out.printf("Minutos: %d\n",minutos);
        System.out.printf("Segundos: %d",segundos);


    }

    public static void ejercicio6() {

        double valor_compra;
        System.out.println("Valor de la compra(entre 0.00 y 500.00): ");
        valor_compra = lectorTeclado.nextFloat();

        double iva;
        System.out.println("IVA(entre 0 y 25%): ");
        iva = lectorTeclado.nextInt();

        double compra = valor_compra - (valor_compra * iva / 100);
        double iva_resto = valor_compra - compra;

        System.out.printf("Compra: %.2f\n",compra);
        System.out.printf("IVA: %.2f\n",iva_resto);
        System.out.println("======");
        System.out.printf("%.2f",valor_compra);

    }

    public static void ejercicio7(){

        System.out.println("Escribe un radio entero");
        int radio = lectorTeclado.nextInt();
        double longitudCircunferencia = 2 * Math.PI * radio;
        System.out.println("Longitud de la circunferencia: "+longitudCircunferencia);
        double areaCirculo = Math.PI * Math.pow(radio,2);
        System.out.println("Area del circulo: "+areaCirculo);
    }

    public static void ejercicio8(){

        System.out.println("Escribe los grados centigrados");
        float c = lectorTeclado.nextFloat();
        float f = ((9*c) / 5) + 32;
        float k = c + 273.15F;
        System.out.printf("Farenheit: %.2f   Kelvin: %.2f\n",f,k);
        System.out.println("Escribe los grados Farenheit");
        f = lectorTeclado.nextFloat();
        c = (5*(f-32))/9;
        k = c + 273.15F;
        System.out.printf("Centigrados: %.2f   Kelvin: %.2f\n",c,k);
        System.out.println("Escribe los grados Kelvin");
        k = lectorTeclado.nextFloat();
        c = k - 273.15F;
        f = ((9*c)/5)+32;
        System.out.printf("Centigrados: %.2f   Farenheit: %.2f",c,f);
    }

    public static void ejercicio9(){

        System.out.println("Introduce el numero de bebidas");
        int bebidas = lectorTeclado.nextInt();
        System.out.println("Introduce el numero de bocadillos");
        int bocadillos = lectorTeclado.nextInt();
        System.out.println("Introduce el precio de cada bebida");
        float precioBebida = lectorTeclado.nextFloat();
        System.out.println("Introduce el precio de cada bodacillo");
        float precioBocadillo = lectorTeclado.nextFloat();
        System.out.println("Introduce el numero de alumnos que realizaron la compra");
        int alumnos = lectorTeclado.nextInt();
        float costeBebidas = bebidas * precioBebida;
        float costeBocadillos = bocadillos * precioBocadillo;
        float total = costeBebidas + costeBocadillos;
        System.out.printf("ARTICULO     CANTIDAD   PRECIO   COSTE\n" +
                        "========     ========  ========  ======\n" +
                        "Bebida             %d      %.2f     %.2f\n" +
                        "Bocadillo          %d      %.2f    %.2f\n" +
                        "                                 ======\n" +
                        "TOTAL                             %.2f\n" +
                        "_______________________________________",bebidas,precioBebida,costeBebidas,bocadillos,
                precioBocadillo,costeBocadillos,total);
    }

    public static void ejercicio10(){

        System.out.println("Introduce las decenas de mil");
        int decenaDeMil = lectorTeclado.nextInt();
        System.out.println("Introduce las unidades de mil");
        int unidadesDeMil = lectorTeclado.nextInt();
        System.out.println("Introduce las centenas");
        int centenas = lectorTeclado.nextInt();
        System.out.println("Introduce las decenas");
        int decenas = lectorTeclado.nextInt();
        System.out.println("Introduce las unidades");
        int unidades = lectorTeclado.nextInt();
        System.out.printf("Numero introducido: %d%d%d%d%d",decenaDeMil,unidadesDeMil,decenas,centenas,unidades);
    }

    public static void ejercicio11(){
        System.out.println("Escribe un numero entero entre 0 y 100");
        int numero = lectorTeclado.nextInt();
        boolean par = (numero%2)==0;
        System.out.println("Par: "+par);
        par = numero > 50;
        System.out.println("Mayor que 50: "+par);

    }

    public static void ejercicio12(){
        System.out.println("Escribe una palabra");
        String palabra1 = lectorTeclado.nextLine();
        System.out.println("Escribe una palabra");
        String palabra2 = lectorTeclado.nextLine();
        boolean igualdad = palabra1.equals(palabra2);
        System.out.println("Son iguales: "+igualdad);
        boolean tamaño = palabra1.compareTo(palabra2)<0;
        System.out.println("La primera es menor que la segunda: "+igualdad);
        boolean distincion = !igualdad;
        System.out.println("Son distintas: "+distincion);

    }

    public static void ejercicio13(){
        System.out.println("Escribe un numero entre 0 y 9");
        int n1 = lectorTeclado.nextInt();
        System.out.println("Escribe un numero entre 0 y 9");
        int n2 = lectorTeclado.nextInt();
        boolean parImpar = ((n1%2)==0) && ((n2%1)==0);
        System.out.println("El primero es par y el segundo impar: "+parImpar);
        boolean inferiorSuperior = ((n1 > (n2 * 2)) && (n1 < 8));
        System.out.println("El primero es superior al doble del segundo e inferior a 8: "+inferiorSuperior);
        boolean igualesDiferencia = ((n1==n2) | Math.abs(n2-n1)<2);
        System.out.println("Son iguales o la diferencia entre el primero y el segundo es menor que 2: "+!igualesDiferencia);
        //no entiendo por qué la ultima tiene que ser true si ni son iguales ni la diferencia entre el primero y el segundo
        //es menor que 2, debería ser false en cualquier caso, pero yo pongo el ! para que la respuesta sea lo contrario
        //y de true en vez de false
    }

    public static void ejercicio14(){
        System.out.println("Introduce tu edad");
        int edad = lectorTeclado.nextInt();
        System.out.println("Introduce tu nivel de estudios");
        int nivelEstudios = lectorTeclado.nextInt();
        System.out.println("Introduce tus ingresos" );
        double ingresos = lectorTeclado.nextInt();
        boolean requisitos = ((edad>40) && (5<=nivelEstudios && nivelEstudios>=8) && (ingresos<15000));
        System.out.println("Mas de 40 años y estudios entre 5 y 8 y gana menos de 15000: "+requisitos);
    }

    public static void ejercicio15(){
        System.out.println("Introduce un numero entero");
        int numero = lectorTeclado.nextInt();
        numero += 5;
        System.out.println("Incrementar 5 unidades: "+numero);
        numero -= 3;
        System.out.println("Decrementar 3 unidades: "+numero);
        numero *= 10;
        System.out.println("Multiplicar por 10: "+numero);
        numero /= 2;
        System.out.println("Dividir entre 2: "+numero);
    }

}


