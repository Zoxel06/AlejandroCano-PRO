package model;

public class Videojuego {

    private String titulo, desarrollador;
    private int anioLanzamiento;
    private double precio;
    private ClasificacionEdad clasificacionEdad;

    public Videojuego() {
    }

    public Videojuego(String titulo, String desarrollador, int anioLanzamiento, double precio, ClasificacionEdad clasificacionEdad) {
        this.titulo = titulo;
        this.desarrollador = desarrollador;
        this.anioLanzamiento = anioLanzamiento;
        this.precio = precio;
        this.clasificacionEdad = clasificacionEdad;
    }

    public double calcularPrecioFinal() {

        return getPrecio();
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", desarrollador='" + desarrollador + '\'' +
                ", anioLanzamiento=" + anioLanzamiento +
                ", precio=" + precio +
                ", clasificacionEdad=" + clasificacionEdad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ClasificacionEdad getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(ClasificacionEdad clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }
}
