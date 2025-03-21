package controller;

import model.Doctor;
import model.Paciente;
import utils.CitaException;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Centro<P extends Paciente, D extends Doctor> {
    static Scanner scanner = new Scanner(System.in);
    private ArrayList<P> listaPacientes;
    private ArrayList<D> listaDoctores;

    public Centro() {
        listaDoctores = new ArrayList<>();
        listaPacientes = new ArrayList<>();
    }

    public void admitirPaciente(P paciente) {
        listaPacientes.add(paciente);
    }

    public void contratarDoctor(D doctor) {
        listaDoctores.add(doctor);
    }

    public void verDoctores() {
        for (int i = 0; i < listaDoctores.size(); i++) {
            System.out.println("Nombre: " + listaDoctores.get(i).getNombre());
            System.out.println("Apellido: " + listaDoctores.get(i).getApellido());
            System.out.println("Numero de colegiado: " + listaDoctores.get(i).getNumeroColegiado());
            System.out.println();
        }
    }

    public void verPacientes() {
        for (int i = 0; i < listaPacientes.size(); i++) {
            System.out.println("Nombre: " + listaPacientes.get(i).getNombre());
            System.out.println("Apellido: " + listaPacientes.get(i).getApellido());
            System.out.println("NSS: " + listaPacientes.get(i).getNss());
            System.out.println("Dolencia: " + listaPacientes.get(i).getDolencia());
            System.out.println();
        }
    }

    public void pedirCita() {
        System.out.println("Dime el nss del paciente");
        int nss = scanner.nextInt();

        for (P paciente : listaPacientes){

        }

        System.out.println("Dime  la especialidad para la cita");
        String especialidad = scanner.next();
        if (especialidad.equalsIgnoreCase("General")) {
            System.out.println("Cita asignada correctamente");
        } else if (especialidad.equalsIgnoreCase("Digestivo")) {
            System.out.println("Cita asignada correctamente");
        } else if (especialidad.equalsIgnoreCase("Traumatologia")) {
            System.out.println("Cita asignada correctamente");
        } else {
            System.out.println("El centro no dispone de esta especialidad");
        }
    }

    public void verCitasPacientes() {

    }

    public void verCitasDoctores() {

    }

}
