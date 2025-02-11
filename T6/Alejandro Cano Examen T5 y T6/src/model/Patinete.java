package model;

public class Patinete extends Vehiculo{

    private String autonomia;

    public Patinete(){

    }

    public Patinete(int numSerie, double precioVenta, String marca, String modelo, String estado, String autonomia) {
        super(numSerie, precioVenta, marca, modelo, estado);
        this.autonomia = autonomia;
    }


    public String getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(String autonomia) {
        this.autonomia = autonomia;
    }
}
