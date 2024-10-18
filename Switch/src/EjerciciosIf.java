import java.util.Scanner;
public class EjerciciosIf {
    static Scanner lectorTeclado = new Scanner(System.in);

    public static void main(String[] args) {
        //ejercicio1();
        //ejercicio2();
        ejercicio3();
    }

    public static void ejercicio1(){
        System.out.println("Dime un numero");
        int a = lectorTeclado.nextInt();
        String s = "Es impar";
        if (a%2==0){
            s = "Es par";
        }

        System.out.println(s);
    }

    public static void ejercicio2(){
        System.out.println("Dime un numero");
        int a = lectorTeclado.nextInt();
        if (a%2==0) {
            a+=1;
        }
        else if (a<10) {
            a-=1;
        }
        else if (a<100) {
            a*=2;
            a+=1;
        }
        else {
            a = 0;
        }
        System.out.println("El nuevo valor del numero es: "+a);

    }

    public static void ejercicio3(){
        int a = lectorTeclado.nextInt();
        if (a<0) {
            System.out.println("Negativo");
        }
        else if (a<10) {
            System.out.println("1 digito");
        }
        else if (a<100) {
            System.out.println("2 digitos");
        }
        else if (a<1000) {
            System.out.println("3 digitos");
        }

    }
}

