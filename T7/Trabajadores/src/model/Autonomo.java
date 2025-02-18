package model;

public class Autonomo extends Trabajador implements Sindicador{

    private int cuotaSS;

    public Autonomo(){

    }

    public Autonomo(String nombre, String apellido, int nSS, double salario, int cuotaSS) {
        super(nombre, apellido, nSS, salario);
        this.cuotaSS = cuotaSS;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Cuotas: " + cuotaSS);
    }

    @Override
    public void calcularSalarioMes() {

        double salarioMes = getSalario() - (cuotaSS * 12);
        System.out.println("El salario mensual es: " + salarioMes);

    }

    public int getCuotaSS() {
        return cuotaSS;
    }

    public void setCuotaSS(int cuotaSS) {
        this.cuotaSS = cuotaSS;
    }

    @Override
    public void realizarHuelga() {
        System.out.println("El autonomo secunda la huelga automaticamente");
    }
}
