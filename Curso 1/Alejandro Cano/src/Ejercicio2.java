import java.util.Scanner;
public class Ejercicio2 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        int numerorandom = (int)(Math.random()*9000)+1000;
        System.out.println("Introduce un numero entre el 1000 y el 9999");
        int numero = scanner.nextInt();
        int millares = numero/1000;
        int centenas = (millares%100)/100;
        int decenas = (centenas%100)/100;
        int unidades = (decenas%100)/100;


        System.out.println("Has acertado las unidades: "+(unidades==unidades));
        System.out.println("Has acertado las decenas: "+(decenas==decenas));
        System.out.println("Has acertado las centenas: "+(centenas==centenas));
        System.out.println("Has acertado los millares: "+(millares==millares));
        System.out.println("Has acertado todo: "+(numero==numerorandom));

        //esta mal, corregir de lo que mande Borja
    }
}
