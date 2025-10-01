package model3;

public class Circulo {

    private double radio, diametroCirculo, areaCirculo;

    public Circulo() {

    }

    public Circulo(double radio) {
        this.radio = radio;
    }

    public double calcularAreaCirculo(){
        double pi = 3.14;
       areaCirculo = pi * Math.pow(radio,2);
       return areaCirculo;
    }

    public double calcularDiametroCirculo(){
        diametroCirculo = 2 * radio;
        return diametroCirculo;
    }

}
