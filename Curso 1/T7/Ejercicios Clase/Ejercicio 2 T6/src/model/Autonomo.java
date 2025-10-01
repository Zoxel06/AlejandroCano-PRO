package model;

public class Autonomo extends Trabajador{

    private double sueldo;
    private boolean contratado;


    public Autonomo(){

    }

    public Autonomo(String nombre, String apellido, String dni, double sueldo, boolean contratado) {
        super(nombre, apellido, dni);
        this.sueldo = sueldo;
        this.contratado = contratado;
    }


    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println(" Sueldo: " + sueldo + " Contratado: " + contratado);
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public boolean isContratado() {
        return contratado;
    }

    public void setContratado(boolean contratado) {
        this.contratado = contratado;
    }
}
