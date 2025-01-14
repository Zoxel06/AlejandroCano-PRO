package ejercicio1;

import ejercicio1.controller.Lista;

import java.util.Scanner;

public class Entrada {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Lista listarProductos = new Lista();

        listarProductos.agregarProductos();
        listarProductos.listarProductos();


    }
}
