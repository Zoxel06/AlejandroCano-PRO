package controller;

import model.Coche;

import java.util.ArrayList;
import java.util.Comparator;
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
        boolean apto = true;

        if (listaCoches.size() == 8 || coche.getCv() > 300) {
            System.out.println("No puede inscribirse");
            apto = false;
        } else {

            for (int i = 0; i < listaCoches.size(); i++) {
                if (listaCoches.get(i).getMatricula().equalsIgnoreCase(coche.getMatricula())) {
                    System.out.println("Imposible, matricula duplicada");
                    apto = false;
                }
            }
            if (apto) {
                System.out.println("Inscripción realizada correctamente");
                listaCoches.add(coche);
            }
        }
    }


    public void descalificarParticipante(String matricula) {
        System.out.println();
        if (listaCoches.contains(matricula)) {
            for (int i = 0; i < listaCoches.size(); i++) {
                listaCoches.remove(listaCoches.get(2).equals(matricula));
                System.out.println("Participante descalificado correctamente");
            }
        } else {
            System.out.println("No se ha inscrito ningun coche con esta matricula");
        }

    }

    public void mostrarParticipantes() {
        System.out.println();
        for (int i = 0; i < listaCoches.size(); i++) {
            System.out.println("Participante " + (i + 1) + ": " + listaCoches.get(i).getMarca() +
                    " " + listaCoches.get(i).getModelo() + " " + listaCoches.get(i).getMatricula()
                    + " " + listaCoches.get(i).getCv());
        }
    }

    public void iniciarCarrera() {
        if (listaCoches.size() == 8 && vueltas > 0){
            for (int i = 0; i <vueltas; i++) {
                for (Coche item : listaCoches) {
                    int kmAleatorio = (int) ((Math.random() * 51) + 50);
                    item.setKm(kmAleatorio);
                }
                System.out.println();
                System.out.println("Vuelta " + (i+1) + ":");
                mostrarClasificacion();
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    throw  new RuntimeException(e);
                }
            }
        }
    }

    public void ordenarParticipantes() {
        listaCoches.sort(new Comparator<Coche>() {
            @Override
            public int compare(Coche o1, Coche o2) {
                if (o1.getKm() > o2.getKm()) {
                    return -1;
                } else if (o1.getKm() < o2.getKm()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

    }


    public void mostrarGanador(){
        ordenarParticipantes();
        System.out.println();
        System.out.println("El ganador es el participante: " + listaCoches.get(0).getMarca() + " " + listaCoches.get(0).getModelo() +
                " " + listaCoches.get(0).getMatricula() + " " + listaCoches.get(0).getCv() + "cv " +
                listaCoches.get(0).getKm() + "km");
    }

    public void mostrarClasificacion(){
        ordenarParticipantes();
        System.out.println("La clasificacion es:");
        for (int i = 0; i < listaCoches.size(); i++) {
            System.out.println((i + 1) + "º: " + listaCoches.get(i).getMarca() + " " + listaCoches.get(i).getModelo() +
                    " " + listaCoches.get(i).getMatricula() + " " + listaCoches.get(i).getCv() + "cv " +
                    listaCoches.get(i).getKm() + "km");
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
