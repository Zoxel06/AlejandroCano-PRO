import model.*;

import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {


        //Tarea tarea = new Tarea("Practica DI", "Esta practica de DI es hacer un programa todolist", 2);

        //tarea.asignarPersona(new Persona("Mario","del Amo","1234A",19));
        //tarea.asignarPersona(new Persona("Tania","del Amo","1234A",19));
        //tarea.asignarPersona(new Persona("Bot","del Amo","1234B",19));
        //tarea.eliminarPersona("1234A");
        //tarea.mostrarDatos();

        /*
        tarea.agregarEncargo(new Encargos(1, "Planificar entrega"));
        tarea.agregarEncargo(new Encargos(2, "Codificar la practica"));
        tarea.agregarEncargo(new Encargos(3, "Grabar video"));
        tarea.agregarEncargo(new Encargos(4, "Entregar practica"));
        tarea.eliminarEncargo(4);
        tarea.agregarEncargo(new Encargos(4, "Subir practica"));
         */

        //tarea.listarEncargos();
        //tarea.buscarEncargo(4);
        //tarea.completarEncargo(3);
        //tarea.buscarEncargo(3);

        TareaProfesional tareaProfesional = new TareaProfesional("TareaP1", "descripcion", true, 4, 100000, new Date());
        TareaPersonal tareaPersonal = new TareaPersonal("TareaPe2", "descripcion", 2, "Alcorcon");

        Persona persona1 = new Persona("Alejandro","Cano","1234A",19);
        tareaProfesional.asignarPersona(persona1);
        tareaProfesional.enviarRecordatorio();

        ArrayList<Tarea> tareas = new ArrayList<>();
        tareas.add(tareaPersonal);
        tareas.add(tareaProfesional);
        for (Tarea tarea1 : tareas) {
            tarea1.enviarRecordatorio();
        }

    }

}
