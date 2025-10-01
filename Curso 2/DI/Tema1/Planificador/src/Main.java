import controller.Planificador;
import model.Prioridad;
import model.Tareas;
import model.TareasOcio;
import model.TareasTrabajo;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Planificador planificador = new Planificador();


        int opcion = 0;

        do {

            System.out.println("Elije una opcion");
            System.out.println("1. Registrar tarea");
            System.out.println("2. Modificar tarea");
            System.out.println("3. Listar tareas");
            System.out.println("4. Registrar tarea");
            System.out.println("5. Completar tarea");
            System.out.println("6. Listar tareas completas");
            System.out.println("7. Listar tareas incompletas");
            System.out.println("8. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    int tipo, nPrioridad;
                    int id;
                    String titulo, descripcion, fecha, ubicacion, proyecto;
                    double presupuesto;
                    boolean completada;
                    Prioridad prioridad = null;


                    Tareas tarea = null;

                    System.out.println("Elije el tipo de tarea que quieres realizar");
                    System.out.println("1. Tarea de ocio");
                    System.out.println("2. Tarea de trabajo");
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
                            System.out.println("Opcion no permitida");
                            break;
                    }

                    if (tipo == 1) {
                        System.out.println("Introduce la ubicacion");
                        ubicacion = scanner.next();
                        System.out.println("Introduce el presupuesto");
                        presupuesto = scanner.nextDouble();

                        tarea = new TareasOcio(id, titulo, descripcion, fecha, prioridad, ubicacion, presupuesto);
                        planificador.registrarTarea(tarea);
                    } else if (tipo == 2) {
                        System.out.println("Introduce el proyecto");
                        proyecto = scanner.next();
                        tarea = new TareasTrabajo(id, titulo, descripcion, fecha, prioridad, proyecto);
                        planificador.registrarTarea(tarea);
                    } else {
                        System.out.println("Opcion no permitida");
                    }

                    break;
                case 2:
                    System.out.println("Dame el id de la tarea que quieres modificar");
                    id = scanner.nextInt();
                    planificador.modificarTarea(id);
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
                case 8:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no permitida");
                    break;
            }

        } while (opcion != 8);


    }

}
