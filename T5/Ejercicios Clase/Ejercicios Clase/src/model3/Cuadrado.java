package model3;

public class Cuadrado {

    private int base, altura;
    private double areaCuadrado, perimetroCuadrado;

    public Cuadrado(){

    }

    public Cuadrado(int base, int altura){
        this.base = base;
        this.altura = altura;
    }

    public double calcularAreaCuadrado(){
        areaCuadrado = base * altura;
        return areaCuadrado;
    }

    public double calcularPerimetroCuadrado(){
        perimetroCuadrado = (altura*2) + (base*2);
        return perimetroCuadrado;
    }

}
