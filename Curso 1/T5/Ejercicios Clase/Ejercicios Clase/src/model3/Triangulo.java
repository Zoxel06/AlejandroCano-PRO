package model3;

public class Triangulo {

    private int base, altura;

    public Triangulo(){

    }

    public Triangulo(int base, int altura){
     this.base = base;
     this.altura = altura;
    }

    public double calcularAreaTriangulo(){
        double areaTriangulo = (double) (base * altura) / 2;

        return areaTriangulo;
    }

}
