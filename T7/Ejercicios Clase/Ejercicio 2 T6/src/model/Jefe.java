package model;

public class Jefe extends Trabajador{

    double acciones, beneficio;  // acciones --> porcentaje de acciones de la empresa


    public Jefe(){

    }

    public Jefe(String nombre, String apellido, String dni, double acciones, double beneficio) {
        super(nombre, apellido, dni);
        this.acciones = acciones;
        this.beneficio = beneficio;
    }


    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println(" Acciones: " + acciones + " Beneficio: " + beneficio);
    }

    public double getAcciones() {
        return acciones;
    }

    public void setAcciones(double acciones) {
        this.acciones = acciones;
    }

    public double getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(double beneficio) {
        this.beneficio = beneficio;
    }
}
