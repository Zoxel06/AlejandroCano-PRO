import model.Coche;
import model.Motor;
import model.Vehiculo;

public class Entrada {

    public static void main(String[] args) {
        Coche coche = new Coche(new Motor(200,20),"ABC123","delantera");
        coche.mostrarDatos();
    }

}
