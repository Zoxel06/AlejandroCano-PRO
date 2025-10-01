package controller;

import model.Audio;
import model.Libro;
import model.Multimedia;
import model.Video;

import java.util.ArrayList;
import java.util.Scanner;

public class Collection {
    static Scanner scanner = new Scanner(System.in);
    private ArrayList<Multimedia> listaMultimedias;

    public Collection(ArrayList<Multimedia> listaMultimedias) {
        this.listaMultimedias = listaMultimedias;
    }

    public void añadirMultimedia(Multimedia item) {


    }

    public void eliminarMultimedia(String id) {

        System.out.println("Dime el id de lo que quieras eliminar");
        id = scanner.next();

        for (Multimedia item : listaMultimedias) {
            if (item.getId().equalsIgnoreCase(id)) {
                listaMultimedias.remove(item);
            }
        }

    }


    public void listarMultimedia(int tipo) {

        for (Multimedia item : listaMultimedias) {
            switch (tipo) {
                case 1:
                    if (item instanceof Libro){
                        item.mostrarDatos();
                    }
                    break;
                case 2:
                    if (item instanceof Audio){
                        item.mostrarDatos();
                    }
                    break;
                case 3:
                    if (item instanceof Video){
                        item.mostrarDatos();
                    }
                    break;
                case 4:
                    item.mostrarDatos();
                    break;
            }
            /*if (item instanceof Libro){
                item.mostrarDatos();
            } else if (item instanceof Video) {
                item.mostrarDatos();
            } else if (item instanceof Audio) {
                item.mostrarDatos();
            }
             */
        }

    }

    public void buscarPersona() {


    }

}
