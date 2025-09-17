package model;

public class Main {

    public static void main(String[] args) {

        Tarea tarea = new Tarea("Practica DI","Esta practica de DI es hacer un programa todolist",2);

        tarea.asignarPersona(new Persona("Mario","del Amo","1234A",19));
        tarea.asignarPersona(new Persona("Tania","del Amo","1234A",19));
        tarea.asignarPersona(new Persona("Bot","del Amo","1234B",19));
        tarea.eliminarPersona("1234A");
        tarea.mostrarDatos();



    }

}
