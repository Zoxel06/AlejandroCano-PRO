package model;

public class Coche {

    // propiedades
    private String marca ,modelo, color, matricula, bastidor;
    private double precio;
    private int cv, par;

    // constructores --> por defecto tengo el constructor vacio sin escribir nada
    public Coche(){
        // inicializar el objeto y por ende todos los atributos de este
    }

    // metodos
    public void mostrarDatos(){
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

}
