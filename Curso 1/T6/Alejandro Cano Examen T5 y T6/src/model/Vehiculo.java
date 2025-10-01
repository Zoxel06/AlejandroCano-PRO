package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Vehiculo extends Empresa {

    static Scanner scanner = new Scanner(System.in);
    private int numSerie;
    private double precioVenta;
    private String marca, modelo, estado;
    private ArrayList<Vehiculo> stock;

    public Vehiculo() {

    }

    public Vehiculo(int numSerie, double precioVenta, String marca, String modelo, String estado) {
        this.numSerie = numSerie;
        this.precioVenta = precioVenta;
        this.marca = marca;
        this.modelo = modelo;
        this.estado = estado;
    }

    public void repararVehiculo(){

    }

    public int getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Vehiculo> getStock() {
        return stock;
    }

    public void setStock(ArrayList<Vehiculo> stock) {
        this.stock = stock;
    }

}
