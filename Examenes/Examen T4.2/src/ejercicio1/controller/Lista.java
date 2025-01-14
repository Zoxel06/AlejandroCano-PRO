package ejercicio1.controller;

import java.util.ArrayList;

public class Lista {

    ArrayList<Object> listaProductos;

    public Lista() {
        listaProductos = new ArrayList<>();
    }



    public void agregarProductos(){

            listaProductos.add("Silla - 20$");
            listaProductos.add("Mesa - 30$");
            listaProductos.add("Television - 200$");
            listaProductos.add("Sofa - 50$");
            listaProductos.add("PS5 - 500$");
            listaProductos.add("Telefono - 199$");

    }

    public void listarProductos(){

        for (int i = 0; i < listaProductos.size(); i++) {
                System.out.println(listaProductos.get(i));

        }
    }

}
