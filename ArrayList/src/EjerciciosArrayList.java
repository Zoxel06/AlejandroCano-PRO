import java.util.ArrayList;
import java.util.Scanner;

public class EjerciciosArrayList {

    private static Scanner scanner = new Scanner(System.in);

    private static ArrayList<String> listaPalabras = new ArrayList();
    private static ArrayList<Integer> listaNumeros = new ArrayList();

    public static void main(String[] args) {
        ejercicio1();
        //ejercicio2();
    }

    public static void ejercicio1(){
        for (int i = 0; i < 10; i++) {
            agregarPalabras();
        }
    }

    public static void agregarPalabras(){
        listaPalabras.add("Hola");
        listaPalabras.add("Hola");
        listaPalabras.add("Hola");
        listaPalabras.add("Hola");
        listaPalabras.add("Hola");
        listaPalabras.add("Hola");
        listaPalabras.add("Hola");
        listaPalabras.add("Hola");
        listaPalabras.add("Hola");
        listaPalabras.add("Hola");
    }

    public static void ejercicio2(){

    }

    public static void agregarNumero(int numero){
        for (int i = 0; i < 20; i++) {
            agregarNumero((int) (Math.random()*101));
        }
    }



}
