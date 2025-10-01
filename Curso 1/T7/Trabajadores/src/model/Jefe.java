package model;

public class Jefe extends Trabajador implements Empleador{

    public Jefe(){

    }

    public Jefe(String nombre, String apellido, int nSS, double salario) {
        super(nombre, apellido, nSS, salario);
    }

    @Override
    public void calcularSalarioMes() {
        double salarioMes = 0;
        System.out.println("El salario mensual es: " + salarioMes);
    }


    @Override
    public boolean realizarTrabajo(int nHoras) {
        System.out.println("El jefe se dispone a realizar el trabajo");
        if (nHoras > 2){
            System.out.println("Trabajo completado");
        }

        return false;
    }

}
