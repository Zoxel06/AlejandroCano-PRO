package model;

import java.util.Scanner;

public class Coche {

    // propiedades
    private String marca, modelo, color, matricula, bastidor;
    private double precio;
    private int cv, par, km;
    // puedo acceder a estos atributos privados gracias a los getter y los setter!!

    // constructores --> por defecto tengo el constructor vacio sin escribir nada

    public Coche() {
        // inicializar el objeto y por ende todos los atributos de este
        // para poder ejecutar funciones cuando el objeto se crea
        matricula = "sin definir";
        modelo = "sin definir";
        marca = "sin definir";
        bastidor = "sin definir";
        color = "sin definir";
    }

    public Coche(String marca, String modelo, String color) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }

    public Coche(String marca, String modelo, int cv) {
        this.marca = marca;
        this.modelo = modelo;
        this.cv = cv;
        par = (int) Math.pow(cv * 2, 2);
    }

    // inicializo el coche con marca modelo color matricula cv precio bastidor
    // par --> calcula
    // precio --> lo que me dan + 15%

    public Coche(String marca, String modelo, String color, String matricula, int cv, double precio, String bastidor) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.matricula = matricula;
        this.bastidor = bastidor;
        this.cv = cv;
        this.precio = (precio * 1.15);
        par = (int) Math.pow(cv * 2, 2);
    }

    public Coche(String marca, String modelo, String matricula, int cv) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.cv = cv;
    }


    // metodos
    public void mostrarDatos() {
        System.out.println("La marca es: " + marca);
        System.out.println("El modelo es: " + modelo);
        System.out.println("Los cv son: " + cv);
        System.out.println("El par es: " + par);
        System.out.println("El color es: " + color);
        System.out.println("El n bastidor es: " + bastidor);
        System.out.println("La matricula es: " + matricula);
        System.out.println("El precio es: " + precio);

    }

    // metodos especiales


    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getBastidor() {
        return bastidor;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCv() {
        return cv;
    }

    public int getPar() {
        return par;
    }

    public int getKm() {
        return km;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setBastidor(String bastidor) {
        this.bastidor = bastidor;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public void setKm(int km) {
        this.km += km;
    }

}


