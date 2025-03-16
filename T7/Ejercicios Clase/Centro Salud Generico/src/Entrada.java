import controller.Centro;
import model.Cita;
import model.Doctor;
import model.Paciente;
import utils.CitaException;

import javax.print.Doc;
import java.util.Scanner;

public class Entrada {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Centro centro = new Centro();

        int opcion = 0;

        do {
            System.out.println("1. Admitir paciente");
            System.out.println("2. Contratar doctor");
            System.out.println("3. Ver todos los pacientes");
            System.out.println("4. Ver todos los doctores");
            System.out.println("5. Pedir cita");
            System.out.println("6. Ver todas las citas de un paciente");
            System.out.println("7. Ver todas las citas de un doctor");
            System.out.println("8. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Dime el nombre");
                    String nombrePaciente = scanner.next();
                    System.out.println("Dime el apellido");
                    String apellidoPaciente = scanner.next();
                    System.out.println("Dime el nss");
                    int nss = scanner.nextInt();
                    System.out.println("Dime la dolencia");
                    String dolencia = scanner.next();

                    Paciente paciente = new Paciente(nombrePaciente, apellidoPaciente, nss, dolencia);

                    centro.admitirPaciente(paciente);
                    break;
                case 2:
                    System.out.println("Dime el nombre");
                    String nombreDoctor = scanner.next();
                    System.out.println("Dime el apellido");
                    String apellidoDoctor = scanner.next();
                    System.out.println("Dime el numero de colegiado");
                    int numeroColegiado = scanner.nextInt();

                    Doctor doctor = new Doctor(nombreDoctor, apellidoDoctor, numeroColegiado);

                    centro.contratarDoctor(doctor);
                    break;
                case 3:
                    centro.verPacientes();
                    break;
                case 4:
                    centro.verDoctores();
                    break;
                case 5:
                    try {
                        centro.pedirCita();
                    }catch (CitaException e){
                        System.out.println(e.getMessage());
                    }
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
            }

        } while (opcion != 8);

    }

}
