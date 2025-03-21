package model;

public class Doctor extends Persona{
    private int numeroColegiado;
    // private String especialidad;

    public Doctor() {
    }

    public Doctor(String nombre, String apellido, int numeroColegiado/*, String especialidad*/) {
        super(nombre, apellido);
        this.numeroColegiado = numeroColegiado;
        //this.especialidad = especialidad;
    }

    public int getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(int numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

}
