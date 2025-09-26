package controller;

import model.Tareas;
import model.TareasOcio;
import model.TareasTrabajo;

import java.util.ArrayList;
import java.util.Scanner;

public class Planificador {
    static Scanner scanner = new Scanner(System.in);


    private ArrayList<Tareas> listaTareas;


    public Planificador() {
        listaTareas = new ArrayList<>();
    }

    public void registrarTarea(Tareas tarea) {
        if (!estaId(tarea.getId())) {
            listaTareas.add(tarea);
            System.out.println("Tarea agregada correctamente a la lista");
        } else {
            System.out.println("No puedes guardar 2 id iguales");
        }

    }

    public void modificarTarea(int id) {
        int campoModificado = 0;

        System.out.println("Dame el id de la tarea que quieres modificar");
        id = scanner.nextInt();

        for (Tareas item : listaTareas) {
            if (item.getId() == id) {
                if (item instanceof TareasOcio){
                    System.out.println("Que campo quieres modificar?");
                    System.out.println("1. Titulo");
                    System.out.println("2. Descripcion");
                    System.out.println("3. Fecha");
                    System.out.println("4. Completada");
                    System.out.println("5. Prioridad");
                    System.out.println("6. Ubicacion");
                    System.out.println("7. Presupuesto");
                    campoModificado = scanner.nextInt();

                    switch (campoModificado){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                    }

                } else if (item instanceof TareasTrabajo) {
                    System.out.println("Que campo quieres modificar?");
                    System.out.println("1. Titulo");
                    System.out.println("2. Descripcion");
                    System.out.println("3. Fecha");
                    System.out.println("4. Completada");
                    System.out.println("5. Prioridad");
                    System.out.println("6. Proyecto");
                    campoModificado = scanner.nextInt();

                    switch (campoModificado){

                    }
                }

            }
        }
        System.out.println("Id no encontrado");

    }

    private boolean estaId(int id) {
        for (Tareas item : listaTareas) {
            if (id == item.getId()) {

                return true;
            }
        }
        return false;
    }

}
