import controller.Agenda;

import javax.naming.ldap.PagedResultsControl;
import java.util.Scanner;

public class Entrada {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        // []



        int opcion;

        do {
            System.out.println("1. Agregar persona");
            System.out.println("2. Salir");
            System.out.println("Elige una opcion");
            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    System.out.println("Introduce tu nombre");
                    String nombre = scanner.next();
                    System.out.println("Introduce tu apellido");
                    String apellido = scanner.next();
                    System.out.println("Introduce tu numero de telefono");
                    int telefono = scanner.nextInt();
                    System.out.println("Introduce tu dni");
                    String dni = scanner.next();

                    if(agenda.agregarPersona(nombre, apellido, telefono, dni)){
                        System.out.println("Persona agregada correctamente");
                    }else {
                        System.out.println("Esta persona ya ha sido agregada anteriormente");
                    }
                    break;
                case 2:
                    switch (agenda.buscarPersona())
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
            }
        } while (opcion!=5);


    }

}
