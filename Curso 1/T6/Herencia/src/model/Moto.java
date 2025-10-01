package model;

public class Moto extends Vehiculo{

    private String empuniadura;

    public Moto(Motor motor, String bastidor, String empuniadura) {
        super(motor, bastidor);
        this.empuniadura = empuniadura;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("LA empuniadura es: " + empuniadura);
    }

    public void metodoMoto(){

    }

}
