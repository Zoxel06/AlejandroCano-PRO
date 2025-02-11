package model;

public class Coche extends Vehiculo {

    private int cv, cc;

    public Coche() {

    }


    public Coche(int numSerie, double precioVenta, String marca, String modelo, String estado, int cv, int cc) {
        super(numSerie, precioVenta, marca, modelo, estado);
        this.cv = cv;
        this.cc = cc;
    }


    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }
}
