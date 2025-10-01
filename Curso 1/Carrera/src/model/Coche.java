package model;

public class Coche {
    private String marca, modelo, matricula;
    private int cv, km;


    public Coche(String marca, String modelo, String matricula, int cv) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.cv = cv;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public void setKm(int km) {
        this.km += km;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getCv() {
        return cv;
    }

    public int getKm() {
        return km;
    }
}
