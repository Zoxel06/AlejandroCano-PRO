package model1;

public class Asignatura {

    private int identificador;
    private double calificacion;

    public Asignatura() {
    }

    public Asignatura(int identificador) {
        this.identificador = identificador;  // el this pertenece al atributo de la clase (arriba) y como le he puesto
    }                                        // el mismo nombre al int de este consrtuctor el "this." es necesario


    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

}