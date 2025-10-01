package model;

public class VideojuegoEstrategia extends Videojuego implements Descargable{

    private int complejidad, duracionPartida;

    public VideojuegoEstrategia() {
    }

    public VideojuegoEstrategia(String titulo, String desarrollador, int anioLanzamiento, double precio, ClasificacionEdad clasificacionEdad, int complejidad, int duracionPartida) {
        super(titulo, desarrollador, anioLanzamiento, precio, clasificacionEdad);
        this.complejidad = complejidad;
        this.duracionPartida = duracionPartida;
    }

    @Override
    public double calcularPrecioFinal() {
        double precioProvisional = 0.0;
        for (int i = 0; i < complejidad; i++) {
            precioProvisional = precioProvisional + (((3*getPrecio()) / 100) + getPrecio());
        }
        setPrecio(precioProvisional);
        return getPrecio();
    }

    @Override
    public String toString() {
        System.out.println(super.toString());
        return
                "complejidad=" + complejidad +
                ", duracionPartida=" + duracionPartida;
    }

    public int getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(int complejidad) {
        this.complejidad = complejidad;
    }

    public int getDuracionPartida() {
        return duracionPartida;
    }

    public void setDuracionPartida(int duracionPartida) {
        this.duracionPartida = duracionPartida;
    }
}
