package model2;

public class Coche {

    private Motor motor;
    private String marca, modelo, matricula;
    private double precioAcumulado;

    public Coche() {
    }

    public Coche(String marca, String modelo, String matricula) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
    }

    public Coche(String marca, String modelo, String matricula, Motor motor) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;

    }

    public boolean hayAveria() {
        boolean averia = true;
        return averia;
    }

    public void acumularAveria(String aceite) {
        precioAcumulado += (int) ((Math.random() * 401) + 100);

    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getPrecioAcumulado() {
        return precioAcumulado;
    }

    public void setPrecioAcumulado(double precioAcumulado) {
        this.precioAcumulado = precioAcumulado;
    }

}
