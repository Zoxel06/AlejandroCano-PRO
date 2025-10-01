import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class EjerciciosTeoria {

    private static Scanner scanner = new Scanner(System.in);

    private static ArrayList<Integer> listaNumeros = new ArrayList();
    private static ArrayList<String> listaPalabras = new ArrayList<>();

    public static void main(String[] args){
        /*System.out.println("Utilizando listas");
        System.out.println("El tamaño inicial de la lista es "+listaNumeros.size());
        anadirNumero((int) (Math.random()*21));
        anadirNumero((int) (Math.random()*21));
        anadirNumero((int) (Math.random()*21));
        obtenerDatos();*/

        listaPalabras.add("Palabra 1");
        listaPalabras.add("Palabra 1");
        listaPalabras.add("Palabra 2");
        listaPalabras.add("Palabra 3");
        listaPalabras.add("Palabra 4");
        listaPalabras.add("Palabra 51");
        listaPalabras.add("Palabra 52");
        listaPalabras.add("Palabra 53");
        listaPalabras.add("Palabra 54");

        listaPalabras.set(4,"Palabra 41");

        listaPalabras.remove("Palabra 5");

        listaPalabras.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("5");
            }
        });

        for ( String item : listaPalabras ) {
            System.out.println(item);
        }
    }

    public static void anadirNumero(int numero){
        listaNumeros.add(numero);
        System.out.println("Numero añadido correctamente");
        System.out.println("Lista actualizada con un size de "+listaNumeros.size());
        System.out.println(listaNumeros);
    }

    public static void obtenerDatos(){
        System.out.println("El primer elemento del array es "+listaNumeros.get(0));
        System.out.println("El ultimo elemento del array es "+listaNumeros.get(listaNumeros.size()-1));
        for ( int item : listaNumeros ) {
            System.out.print(item + "\t");
        }
    }
}
