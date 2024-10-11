import java.util.Scanner;

public class EjerciciosFor {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        //ejercicio1();
        //ejercicio2();
        //ejercicio3();
        ejercicio4();
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
        //ejercicio16();
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


}
