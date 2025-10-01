package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PlataformaJuegos {

    Scanner scanner = new Scanner(System.in);

    private ArrayList<Videojuego> listaVideojuegos;

    public PlataformaJuegos() {
        listaVideojuegos = new ArrayList<>();
    }

    public void aniadirJuego(Videojuego videojuego) {
        System.out.println("Videojuego anñadido con exito");
        listaVideojuegos.add(videojuego);
    }

    public void eliminarJuego(Videojuego videojuego) {
        for (int i = 0; i < listaVideojuegos.size(); i++) {

        }
        System.out.println("Videojuego eliminado con exito");
        listaVideojuegos.remove(videojuego);
    }

    public void ordenarJuegosPrecio() {
        for (Videojuego item : listaVideojuegos) {
            System.out.println(item.toString());
        }
    }

    public void filtrarClasificacion() {
        int opcion = 0;
        System.out.println("Que tipo de juegos quieres ver:");
        System.out.println("1-PEGI9");
        System.out.println("1-PEGI15");
        System.out.println("1-PEGI18");

        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                for (Videojuego item : listaVideojuegos) {
                    if (item.getClasificacionEdad().equals(ClasificacionEdad.PEGI9)) {
                        System.out.println(item.toString());
                    }
                }
                break;
            case 2:
                for (Videojuego item : listaVideojuegos) {
                    if (item.getClasificacionEdad().equals(ClasificacionEdad.PEGI15)) {
                        System.out.println(item.toString());
                    }
                }
                break;
            case 3:
                for (Videojuego item : listaVideojuegos) {
                    if (item.getClasificacionEdad().equals(ClasificacionEdad.PEGI18)) {
                        System.out.println(item.toString());
                    }
                }
                break;
            default:
                System.out.println("Opcion no permitida");
        }
    }

    public void calcularTotalJuegos() {
        double precioTotal = 0;
        for (Videojuego item : listaVideojuegos) {
            precioTotal = precioTotal+ item.getPrecio();
        }
        System.out.println("El precio total de todos los videojuegos es " + precioTotal);
    }

    public ArrayList<Videojuego> getListaVideojuegos() {
        return listaVideojuegos;
    }

    public void setListaVideojuegos(ArrayList<Videojuego> listaVideojuegos) {
        this.listaVideojuegos = listaVideojuegos;
    }
}
