package controller;

import model.Prioridad;
import model.Tareas;
import model.TareasOcio;
import model.TareasTrabajo;

import java.util.ArrayList;
import java.util.Iterator;
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
        for (Tareas item : listaTareas) {
            if (item.getId() == id) {
                if (item instanceof TareasOcio) {
                    System.out.println("Qué campo quieres modificar?");
                    System.out.println("1. Titulo");
                    System.out.println("2. Descripcion");
                    System.out.println("3. Fecha");
                    System.out.println("4. Completada");
                    System.out.println("5. Prioridad");
                    System.out.println("6. Ubicacion");
                    System.out.println("7. Presupuesto");
                    int campo = scanner.nextInt();

                    switch (campo) {
                        case 1:
                            System.out.println("Introduce el nuevo titulo:");
                            item.setTitulo(scanner.next());
                            break;
                        case 2:
                            System.out.println("Introduce la nueva descripcion:");
                            item.setDescripcion(scanner.next());
                            break;
                        case 3:
                            System.out.println("Introduce la nueva fecha:");
                            item.setFecha(scanner.next());
                            break;
                        case 4:
                            System.out.println("¿Está completada? (true/false):");
                            item.setCompletada(scanner.nextBoolean());
                            break;
                        case 5:
                            System.out.println("Nueva prioridad (1-BAJA / 2-MEDIA / 3-ALTA):");
                            int n = scanner.nextInt();
                            switch (n) {
                                case 1:
                                    item.setPrioridad(Prioridad.BAJA);
                                    break;
                                case 2:
                                    item.setPrioridad(Prioridad.MEDIA);
                                    break;
                                case 3:
                                    item.setPrioridad(Prioridad.ALTA);
                                    break;
                                default:
                                    System.out.println("Opción no válida");
                                    break;
                            }
                            break;
                        case 6:
                            System.out.println("Introduce la nueva ubicacion:");
                            ((TareasOcio) item).setUbicacion(scanner.next());
                            break;
                        case 7:
                            System.out.println("Introduce el nuevo presupuesto:");
                            ((TareasOcio) item).setPresupuesto(scanner.nextDouble());
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }
                    return;
                } else if (item instanceof TareasTrabajo) {
                    System.out.println("Qué campo quieres modificar?");
                    System.out.println("1. Titulo");
                    System.out.println("2. Descripcion");
                    System.out.println("3. Fecha");
                    System.out.println("4. Completada");
                    System.out.println("5. Prioridad");
                    System.out.println("6. Proyecto");
                    int campo = scanner.nextInt();

                    switch (campo) {
                        case 1:
                            System.out.println("Introduce el nuevo titulo:");
                            item.setTitulo(scanner.next());
                            break;
                        case 2:
                            System.out.println("Introduce la nueva descripcion:");
                            item.setDescripcion(scanner.next());
                            break;
                        case 3:
                            System.out.println("Introduce la nueva fecha:");
                            item.setFecha(scanner.next());
                            break;
                        case 4:
                            System.out.println("¿Está completada? (true/false):");
                            item.setCompletada(scanner.nextBoolean());
                            break;
                        case 5:
                            System.out.println("Nueva prioridad (1-BAJA / 2-MEDIA / 3-ALTA):");
                            int n = scanner.nextInt();
                            switch (n) {
                                case 1:
                                    item.setPrioridad(Prioridad.BAJA);
                                    break;
                                case 2:
                                    item.setPrioridad(Prioridad.MEDIA);
                                    break;
                                case 3:
                                    item.setPrioridad(Prioridad.ALTA);
                                    break;
                                default:
                                    System.out.println("Opción no válida");
                                    break;
                            }
                            break;
                        case 6:
                            System.out.println("Introduce el nuevo proyecto:");
                            ((TareasTrabajo) item).setProyecto(scanner.next());
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }
                    return;
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

    public void listarTareas() {
        if (listaTareas.isEmpty()) {
            System.out.println("No hay tareas registradas");
        } else {
            for (Tareas item : listaTareas) {
                System.out.println(item);
            }
        }
    }

    public void eliminarTarea(int id) {
        Iterator<Tareas> it = listaTareas.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                System.out.println("Tarea eliminada correctamente");
                return;
            }
        }
        System.out.println("Id no encontrado");
    }

    public void completarTarea(int id) {
        for (Tareas item : listaTareas) {
            if (item.getId() == id) {
                item.setCompletada(true);
                System.out.println("Tarea marcada como completada");
                return;
            }
        }
        System.out.println("Id no encontrado");
    }

    public void listarTareasCompletas() {
        boolean hay = false;
        for (Tareas item : listaTareas) {
            if (item.isCompletada()) {
                System.out.println(item);
                hay = true;
            }
        }
        if (!hay) System.out.println("No hay tareas completas");
    }

    public void listarTareasIncompletas() {
        boolean hay = false;
        for (Tareas item : listaTareas) {
            if (!item.isCompletada()) {
                System.out.println(item);
                hay = true;
            }
        }
        if (!hay) System.out.println("No hay tareas incompletas");
    }
}
