import model.Multimedia;
import model.Persona;
import model.Video;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Entrada {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Collection coleccion = (Collection) new controller.Collection(new ArrayList<>());


        /*int opcion = 0;
        Collection collection = (Collection) new controller.Collection(new ArrayList<>());

        do {

            System.out.println("1. Añadir multimedia");
            System.out.println("2. Eliminar multimedia");
            System.out.println("3. Listar multimedia");
            System.out.println("4. Buscar por persona");
            System.out.println("5. Salir");
            System.out.println("Elige una opcion");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion no permitida, intentalo de nuevo");

            }

        } while (opcion != 5);

         */

    }

}
