import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicios {
    //pedir al usuario un numero a añadir
    //añadirlo a la lista --> añadir 4 numeros
    //si el numero ya esta en la lista, pedir confirmacion
    //si no esta en la lista, agregarlo

    static Scanner scanner = new Scanner(System.in);

    private static ArrayList<Integer> listaNumeros = new ArrayList<>();


    public static void main(String[] args) {
        System.out.println("Utilizando listas");
        System.out.println("El tamaño inicial de la lista es: "+listaNumeros.size());
        anadirNumero(1);
        anadirNumero(4);
        anadirNumero(3);
        anadirNumero(4);
        obtenerNumeros();
    }

    public static void anadirNumero(int numero) {
        if (listaNumeros.contains(numero)) {
            System.out.println("El numero ya esta en la lista, ¿estas seguro de que quieres meterlo?");
            String respuesta = scanner.next();
            if (respuesta.equalsIgnoreCase("si")) {
                listaNumeros.add(numero);
                System.out.println("Numero añadido correctamente");
                System.out.println("Lista actualizada con un size de " + listaNumeros.size());
            } else {
                System.out.println("Saliendo de añadir");
            }
        } else {
            listaNumeros.add(numero);
            System.out.println("Numero añadido correctamente");
            System.out.println("Lista actualizada con un size de " + listaNumeros.size());
        }

    }

    //TODO cambiar el retorno al indice donde esta el repetido
    public static int encontrarNumero(int numero){
        for (int i = 0; i < listaNumeros.size(); i++) {
            if (listaNumeros.get(i)==numero){
                return i;
            }
        }
        return -1;
    }


    public static void obtenerNumeros(){
        System.out.println("Los elementos del array son: ");
        for ( int item : listaNumeros ) {
            System.out.print(item + "\t");
        }
    }
}
