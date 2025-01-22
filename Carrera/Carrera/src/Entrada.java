import controller.Carrera;
import model.Coche;

import java.util.Scanner;

public class Entrada {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Carrera carrera1 = new Carrera(8,"Carrera 1");
        Carrera carrera2 = new Carrera(5,"Carrera 2");
        Carrera carrera3 = new Carrera(10,"Carrera 3");

        carrera1.inscribirParticipante(new Coche("Marca 1","Modelo 1","123A",200));
        carrera1.inscribirParticipante(new Coche("Marca X","Modelo X","123A",550));
        carrera1.inscribirParticipante(new Coche("Marca Y","Modelo Y","123A",250));
        carrera1.inscribirParticipante(new Coche("Marca 3","Modelo 3","345C",270));
        carrera1.inscribirParticipante(new Coche("Marca 4","Modelo 4","456D",275));
        carrera1.inscribirParticipante(new Coche("Marca 5","Modelo 5","567E",250));
        carrera1.inscribirParticipante(new Coche("Marca 6","Modelo 6","678F",180));
        carrera1.inscribirParticipante(new Coche("Marca 7","Modelo 7","789G",222));
        carrera1.inscribirParticipante(new Coche("Marca 8","Modelo 8","890H",320));
        carrera1.inscribirParticipante(new Coche("Marca 9","Modelo 9","890I",120));

        carrera1.descalificarParticipante("345C");

        carrera1.mostrarParticipantes();
    }

}
