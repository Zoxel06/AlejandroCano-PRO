import model.Coche;

import java.util.ArrayList;
import java.util.Scanner;

public class Carrera {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        ArrayList<Coche> listaCoches = new ArrayList<Coche>();

        /*
        Coche cocheOpel = new Coche("Opel","Corsa",120);

        listaCoches.add(new Coche("Ford","Focus","Plata"));
        listaCoches.add(new Coche("Seat","Ibiza","Blanco"));
        listaCoches.add(new Coche("Mercedes","C300",400));
        listaCoches.add(cocheOpel);

        cocheOpel.setCv(cocheOpel.getCv()*2);

        for (Coche coche : listaCoches) {
            coche.mostrarDatos();
        }

         */



    /*
    Un coche tiene un atributo KM;
    lod cuales pueden decir la distancia que ha recorrido un coche
    Para poder hacer una carrera, es necesario que se pida por consola
    los datos de 6 participantes, donde se debe indicar marca, modelo, matricula, cv.
    Una vez indicados todos los participantes, es necesario indicar de cuantas vueltas consta la carrera
    Una vez indicado el numero de vueltas, la carrera comienza. Para ello en cada vuelta
    cada coche recorre un numero de km aleatorio (50-75)
    Una vez se terminan las vueltas el sistema mostrara:

    El ganador es el coche XXXX XXXX XXXXX
    La clasificacion final es;
    1 - XXX XXX XXX XX
    2 - XXX XXX XXX XX
     */


        Coche cocheCarrera = new Coche();

        int vueltas = 0;

        System.out.println("Necesito los datos de los 6 participantes");
        for (int i = 0; i < 6; i++) {
            System.out.println("Introduce marca");
            String marca = scanner.next();
            System.out.println("Introduce modelo");
            String modelo = scanner.next();
            System.out.println("Introduce matricula");
            String matricula = scanner.next();
            System.out.println("Introduce cv");
            String cv = scanner.next();
            listaCoches.add(new Coche(scanner.next(),scanner.next(),scanner.next(),scanner.nextInt(),scanner.nextInt()));
        }

        System.out.println("¿Cuantas vueltas va a tener la carrera?");
        vueltas = scanner.nextInt();

        for (int i = 0; i < vueltas; i++) {
            for (Coche item : listaCoches) {
                int km = (int) (Math.random() * 26) + 50; // (75 - 50 + 1) = 26)
            }
        }


    }
}
