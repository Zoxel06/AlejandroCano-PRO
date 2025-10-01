package model;

public class VideojuegoAccion extends Videojuego implements Descargable{

    private int nivelViolencia;
    private boolean modoMultijugador;

    public VideojuegoAccion() {
    }

    public VideojuegoAccion(String titulo, String desarrollador, int anioLanzamiento, double precio, ClasificacionEdad clasificacionEdad, int nivelViolencia, boolean modoMultijugador) {
        super(titulo, desarrollador, anioLanzamiento, precio, clasificacionEdad);
        this.nivelViolencia = nivelViolencia;
        this.modoMultijugador = modoMultijugador;
    }

    @Override
    public double calcularPrecioFinal() {
        if (nivelViolencia > 3) {
            setPrecio(((5 * getPrecio()) / 100) + getPrecio());
        }
        if (modoMultijugador) {
            setPrecio(((10 * getPrecio()) / 100) + getPrecio());
        }
        return getPrecio();
    }

    @Override
    public String toString() {
        System.out.println(super.toString());
        return
                "nivelViolencia=" + nivelViolencia +
                ", modoMultijugador=" + modoMultijugador;
    }

    public boolean isModoMultijugador() {
        return modoMultijugador;
    }

    public void setModoMultijugador(boolean modoMultijugador) {
        this.modoMultijugador = modoMultijugador;
    }

    public int getNivelViolencia() {
        return nivelViolencia;
    }

    public void setNivelViolencia(int nivelViolencia) {
        this.nivelViolencia = nivelViolencia;
    }
}
