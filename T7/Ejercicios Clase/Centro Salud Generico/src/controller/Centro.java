package controller;

import model.Paciente;

import java.util.ArrayList;
import java.util.Scanner;

public class Centro<LP, LD> {
static Scanner scanner = new Scanner(System.in);
    private ArrayList<LP> listaPacientes;
    private ArrayList<LD> listaDoctores;

    public void admitirPaciente(){
        System.out.println("Dime el nombre");
        String nombre = scanner.next();
        System.out.println("Dime el apellido");
        String apellido = scanner.next();
        System.out.println("Dime el nss");
        int nss = scanner.nextInt();
        System.out.println("Dime la dolencia");
        String dolencia = scanner.next();
        Paciente paciente = new Paciente(nombre,apellido,nss,dolencia);
        // listaPacientes.add(paciente);

    }

}
