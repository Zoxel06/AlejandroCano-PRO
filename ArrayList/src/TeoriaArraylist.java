import java.util.ArrayList;

public class TeoriaArraylist {


    public static void main(String[] args){
        ArrayList lista = new ArrayList();
        ArrayList <Integer> listaNumeros = new ArrayList<>();
        listaNumeros.add(20);
        listaNumeros.add(40);
        listaNumeros.add(60);
        listaNumeros.add(80);

        listaNumeros.set(1,33); //cambia el numero de la posicion 1 a "33"

        listaNumeros.remove(0);
        listaNumeros.get(0); // obtener una posicion

        ArrayList listaPalabras = new ArrayList();
        listaPalabras.add("hola");
        listaPalabras.add("adios");
        listaPalabras.add("dia");

        //listaPalabras.remove(2); es lo mismo que hacer lo de la linea de abajo, pero en integers puede ser lioso si la posicion coincide con un numero del array
        listaPalabras.remove("dia");


        System.out.println(listaNumeros.get(2));
        System.out.println(listaNumeros.contains(8));
        System.out.println(listaNumeros.isEmpty());


        System.out.println(listaNumeros);
        System.out.println(listaPalabras);

        listaNumeros.clear();
        System.out.println(listaNumeros);
    }
}
