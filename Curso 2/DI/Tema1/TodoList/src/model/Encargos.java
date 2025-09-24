package model;

import java.util.ArrayList;

//crear el metodo que permite agregar un encargo
//los encargos deben tener ID unico
// crear el metodo que permite eliminar un encargo
//para ello se pide el ID del encargo y se quita de la lista -> PONER AVISOS
public class Encargos {

    private int id;
    private String descripcion;
    private boolean completada;

    public Encargos() {
    }

    public Encargos(String descripcion, int id, boolean completada) {
        this.id = id;
        this.descripcion = descripcion;
        this.completada = completada;
    }

    public Encargos(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    @Override
    public String toString() {
        return "Encargos{" +
                "descripcion='" + descripcion + '\'' +
                ", id='" + id + '\'' +
                ", completa=" + completada +
                '}';
    }



}
