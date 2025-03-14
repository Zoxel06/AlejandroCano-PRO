package controller;

import model.Paciente;

import java.util.ArrayList;
import java.util.Scanner;

public class Centro<P, D> {
static Scanner scanner = new Scanner(System.in);
    private ArrayList<P> listaPacientes;
    private ArrayList<D> listaDoctores;

    public void admitirPaciente(P paciente){
        System.out.println("Dime el nombre");
        String nombre = scanner.next();
        System.out.println("Dime el apellido");
        String apellido = scanner.next();
        System.out.println("Dime el nss");
        int nss = scanner.nextInt();
        System.out.println("Dime la dolencia");
        String dolencia = scanner.next();
    }

}
