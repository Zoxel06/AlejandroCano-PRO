import dto.ProfesorDTO;
import model.Profesor;
import model.ProfesorExterno;
import model.ProfesorInterno;

import java.util.Scanner;

public class Entrada {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ProfesorDTO profesorDTO = new ProfesorDTO();

        int opcion = 0;

        do {

        System.out.println("1. Mostrar profesores de BD");
        System.out.println("2. Añadir profesor temporal");
        System.out.println("3. Borrar profesor temporal");
        System.out.println("4. Exportar profesores a BD");
        System.out.println("5. Exportar datos a csv");
        System.out.println("6. Exportar datos a obj");
        System.out.println("7. Salir");
        System.out.println("Elije una opcion");

        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:

                profesorDTO.mostrarProfesores();
                break;
            case 2:
                System.out.println("Dime el tipo de profesor");
                System.out.println("1. Externo");
                System.out.println("2. Interno");
                int respuesta = scanner.nextInt();
                switch (respuesta){
                    case 1:
                        System.out.println("Dime el nombre");
                        String nombre1 = scanner.next();
                        System.out.println("Dime el dni");
                        String dni1 = scanner.next();
                        System.out.println("Dime el salario anual");
                        double salarioAnual1 = scanner.nextDouble();
                        System.out.println("Dime el salario mensual");
                        double salarioMensual1 = scanner.nextDouble();
                        profesorDTO.aniadirProfesor(new ProfesorExterno(nombre1,dni1,salarioAnual1,salarioMensual1));
                        break;
                    case 2:
                        System.out.println("Dime el nombre");
                        String nombre2 = scanner.next();
                        System.out.println("Dime el dni");
                        String dni2 = scanner.next();
                        System.out.println("Dime el salario anual");
                        double salarioAnual2 = scanner.nextDouble();
                        System.out.println("Dime las horas");
                        int horas2 = scanner.nextInt();
                        System.out.println("Dime el precio por hora");
                        double precioHora2 = scanner.nextDouble();
                        profesorDTO.aniadirProfesor(new ProfesorInterno(nombre2,dni2,salarioAnual2,horas2,precioHora2));
                    default:
                        System.out.println("Opcion no permitida");
                }

                break;
            case 3:
                System.out.println("Dime el dni del profesor que quieras borrar (9 digitos)");
                String dni = scanner.next();

                profesorDTO.eliminarProfesor(dni);
                break;
            case 4:
                profesorDTO.exportarProfesores();
                break;
            case 5:
                profesorDTO.exportarCsv();
                break;
            case 6:
                profesorDTO.exportarObj();
                break;
            case 7:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opcion no permitida");
        }

    }while (opcion != 7);

    }

}
