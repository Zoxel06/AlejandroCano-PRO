import java.util.Scanner;

public class Entrada {

static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion = 0;

        do {
            System.out.println("\n1. Admitir paciente");
            System.out.println("2. Contratar doctor");
            System.out.println("3. Ver todos los doctores");
            System.out.println("4. Ver todos los pacientes");
            System.out.println("5. Ver todas las citas de un paciente");
            System.out.println("6. Ver todas las citas de un doctor");
            System.out.println("7. Pedir cita");
            System.out.println("8. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    break;
            }

        } while (opcion != 8);

    }

}
