package model;

public class Asalariado extends Trabajador {

    private double retencion;
    private int nPagas;

    public Asalariado(){

    }

    public Asalariado(String nombre, String apellido, int nSS, double salario, double retencion, int nPagas) {
        super(nombre, apellido, nSS, salario);
        this.retencion = retencion;
        this.nPagas = nPagas;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Retencion: " + retencion);
        System.out.println("Numero de pagas: " + nPagas);
    }

    @Override
    public void calcularSalarioMes() {

        double salarioMes = (getSalario() - (getSalario()*retencion)) / nPagas;

        System.out.println("El salario mensual es: " + salarioMes);

    }

    public double getRetencion() {
        return retencion;
    }

    public void setRetencion(double retencion) {
        this.retencion = retencion;
    }

    public int getnPagas() {
        return nPagas;
    }

    public void setnPagas(int nPagas) {
        this.nPagas = nPagas;
    }

}
