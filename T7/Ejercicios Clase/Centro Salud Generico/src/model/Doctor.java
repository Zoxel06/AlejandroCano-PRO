package model;

public class Doctor {
    private String nombre;
    private String apellido;
    private int numeroColegiado;

    public Doctor() {
    }

    public Doctor(String nombre, String apellido, int numeroColegiado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroColegiado = numeroColegiado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(int numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }
}
