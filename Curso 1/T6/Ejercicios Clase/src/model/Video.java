package model;

import java.util.ArrayList;

public class Video extends Multimedia{

    private Persona director;
    private ArrayList<Persona> actores;

    public Video() {
    }

    public Video(String id, String titulo, String formato, int tamanio, Persona autor, Persona director,
                 ArrayList<Persona> actores) {
        super(id, titulo, formato, tamanio, autor);
        this.director = director;
        this.actores = actores;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Director: " + director);
        System.out.println("Actores: ");
        for (Persona item : actores) {
            System.out.println("Actor " + item + ": " + item.getNombre());
        }
    }

    public Persona getDirector() {
        return director;
    }

    public void setDirector(Persona director) {
        this.director = director;
    }

    public ArrayList<Persona> getActores() {
        return actores;
    }

    public void setActores(ArrayList<Persona> actores) {
        this.actores = actores;
    }
}
