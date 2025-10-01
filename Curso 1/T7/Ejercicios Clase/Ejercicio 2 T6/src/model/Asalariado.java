package model;

public class Asalariado extends Trabajador{

    private double sueldo;
    private int nPagas;
    private boolean contratado;


    public Asalariado(){

    }

    public Asalariado(String nombre, String apellido, String dni, double sueldo, int nPagas, boolean contratado) {
        super(nombre, apellido, dni);
        this.sueldo = sueldo;
        this.nPagas = nPagas;
        this.contratado = contratado;
    }


    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println(" Sueldo:" + sueldo + " Numero de pagas: " + nPagas + " Contratado: " + contratado);
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public int getnPagas() {
        return nPagas;
    }

    public void setnPagas(int nPagas) {
        this.nPagas = nPagas;
    }

    public boolean isContratado() {
        return contratado;
    }

    public void setContratado(boolean contratado) {
        this.contratado = contratado;
    }
}
