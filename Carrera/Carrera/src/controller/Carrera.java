package controller;

import model.Coche;

import java.util.ArrayList;
import java.util.Scanner;


public class Carrera {
    static Scanner scanner = new Scanner(System.in);
    private ArrayList<Coche> listaCoches;
    private int vueltas;
    private String nombreCarrera;
    private Coche ganador;


    public Carrera(int vueltas, String nombreCarrera) {
        this.vueltas = vueltas;
        this.nombreCarrera = nombreCarrera;
        listaCoches = new ArrayList<Coche>();
    }

    public void crearCarrera() {
        System.out.println("Como se va a llamar la carrera?");
        nombreCarrera = scanner.next();
        System.out.println("Cuantas vueltas va a tener la carrera?");
        vueltas = scanner.nextInt();
    }

    public void inscribirParticipante(Coche coche) {
        if (listaCoches.size() == 8 || coche.getCv() > 300) {
            System.out.println("No puede inscribirse");

        } else {
            System.out.println("Inscripción realizada correctamente");
            listaCoches.add(coche);
        }
    }


    public ArrayList<Coche> getListaCoches() {
        return listaCoches;
    }

    public void setListaCoches(ArrayList<Coche> listaCoches) {
        this.listaCoches = listaCoches;
    }

    public int getVueltas() {
        return vueltas;
    }

    public void setVueltas(int vueltas) {
        this.vueltas = vueltas;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public Coche getGanador() {
        return ganador;
    }

    public void setGanador(Coche ganador) {
        this.ganador = ganador;
    }
}
