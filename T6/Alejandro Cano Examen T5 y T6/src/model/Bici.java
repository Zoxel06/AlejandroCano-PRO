package model;

public class Bici extends Vehiculo{

    private String tipo;

    public Bici(){

    }

    public Bici(int numSerie, double precioVenta, String marca, String modelo, String estado, String tipo) {
        super(numSerie, precioVenta, marca, modelo, estado);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
