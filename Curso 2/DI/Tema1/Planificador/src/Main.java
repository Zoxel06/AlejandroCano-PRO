import controller.Planificador;
import model.Prioridad;
import model.TareasOcio;
import model.TareasTrabajo;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Planificador planificador = new Planificador();
        int opcion;

        do {
            System.out.println("Elije una opcion");
            System.out.println("1. Registrar tarea");
            System.out.println("2. Modificar tarea");
            System.out.println("3. Listar tareas");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Completar tarea");
            System.out.println("6. Listar tareas completas");
            System.out.println("7. Listar tareas incompletas");
            System.out.println("8. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    int tipo, nPrioridad, id;
                    String titulo, descripcion, fecha, ubicacion, proyecto;
                    double presupuesto;
                    Prioridad prioridad = null;

                    System.out.println("Elije el tipo de tarea");
                    System.out.println("1. Ocio");
                    System.out.println("2. Trabajo");
                    tipo = scanner.nextInt();

                    System.out.println("Introduce el id");
                    id = scanner.nextInt();
                    System.out.println("Introduce el titulo");
                    titulo = scanner.next();
                    System.out.println("Introduce la descripcion");
                    descripcion = scanner.next();
                    System.out.println("Introduce la fecha");
                    fecha = scanner.next();
                    System.out.println("Introduce la prioridad (1-BAJA / 2-MEDIA / 3-ALTA)");
                    nPrioridad = scanner.nextInt();

                    switch (nPrioridad) {
                        case 1:
                            prioridad = Prioridad.BAJA;
                            break;
                        case 2:
                            prioridad = Prioridad.MEDIA;
                            break;
                        case 3:
                            prioridad = Prioridad.ALTA;
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }

                    if (tipo == 1) {
                        System.out.println("Introduce la ubicacion");
                        ubicacion = scanner.next();
                        System.out.println("Introduce el presupuesto");
                        presupuesto = scanner.nextDouble();
                        planificador.registrarTarea(new TareasOcio(id, titulo, descripcion, fecha, prioridad, ubicacion, presupuesto));
                    } else if (tipo == 2) {
                        System.out.println("Introduce el proyecto");
                        proyecto = scanner.next();
                        planificador.registrarTarea(new TareasTrabajo(id, titulo, descripcion, fecha, prioridad, proyecto));
                    } else {
                        System.out.println("Tipo de tarea no válido");
                    }
                    break;

                case 2:
                    System.out.println("Dame el id de la tarea que quieres modificar");
                    id = scanner.nextInt();
                    planificador.modificarTarea(id);
                    break;

                case 3:
                    planificador.listarTareas();
                    break;

                case 4:
                    System.out.println("Dame el id de la tarea que quieres eliminar");
                    id = scanner.nextInt();
                    planificador.eliminarTarea(id);
                    break;

                case 5:
                    System.out.println("Dame el id de la tarea que quieres completar");
                    id = scanner.nextInt();
                    planificador.completarTarea(id);
                    break;

                case 6:
                    planificador.listarTareasCompletas();
                    break;

                case 7:
                    planificador.listarTareasIncompletas();
                    break;

                case 8:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no permitida");
                    break;
            }
        } while (opcion != 8);
    }
}
