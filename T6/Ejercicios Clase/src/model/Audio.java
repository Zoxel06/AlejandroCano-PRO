package model;

public class Audio extends  Multimedia{

    private double duracion;
    private String soporte;

    public Audio(){}

    public Audio(String id, String titulo, String formato, int tamaño, Persona autor, double duracion, String soporte) {
        super(id, titulo, formato, tamaño, autor);
        this.duracion = duracion;
        this.soporte = soporte;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Duracion: " + duracion);
        System.out.println("Soporte: " + soporte);
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }
}
