package model;

public class Moto extends Vehiculo{

    private String transmision;
    private int peso;

    public Moto(){

    }

    public Moto(int numSerie, double precioVenta, String marca, String modelo, String estado, String transmision, int peso) {
        super(numSerie, precioVenta, marca, modelo, estado);
        this.transmision = transmision;
        this.peso = peso;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
