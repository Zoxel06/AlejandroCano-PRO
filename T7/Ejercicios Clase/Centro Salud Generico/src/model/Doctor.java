package model;

public class Doctor extends Persona{
    private int numeroColegiado;

    public Doctor() {
    }

    public Doctor(String nombre, String apellido, int numeroColegiado) {
        super(nombre, apellido);
        this.numeroColegiado = numeroColegiado;
    }

    public int getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(int numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

}
