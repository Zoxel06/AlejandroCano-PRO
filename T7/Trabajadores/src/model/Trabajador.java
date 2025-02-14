package model;

public abstract class Trabajador {

    private String nombre, apellido;
    private int nSS;
    private double salario;

    public Trabajador(){

    }

    public Trabajador(String nombre, String apellido, int nSS, double salario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nSS = nSS;
        this.salario = salario;
    }


    public abstract void calcularSalarioMes();

    public void mostrarDatos(){
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("nSS: " + nSS);
        System.out.println("Salario: " + salario);
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

    public int getnSS() {
        return nSS;
    }

    public void setnSS(int nSS) {
        this.nSS = nSS;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

}
