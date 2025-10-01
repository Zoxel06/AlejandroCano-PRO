package controller;

import model.Asalariado;
import model.Autonomo;
import model.Jefe;
import model.Trabajador;

import java.util.ArrayList;
import java.util.Scanner;

public class Empresa {

    static Scanner scanner = new Scanner(System.in);

    private ArrayList<Trabajador> listaTrabajadores;

    public Empresa() {
        this.listaTrabajadores = new ArrayList<>();
    }


    public void registrarTrabajador() {
        System.out.println("Elije el tipo de trabajador que quieres registrar");
        System.out.println("1. Jefe");
        System.out.println("2. Asalariado");
        System.out.println("3. Autonomo");

        int opcion = 0;
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Dime el nombre");
                String nombre = scanner.next();
                System.out.println("Dime el apellido");
                String apellido = scanner.next();
                System.out.println("Dime el dni");
                String dni = scanner.next();
                System.out.println("Dime las acciones");
                double acciones = scanner.nextDouble();
                System.out.println("Dime el beneficio");
                double beneficio = scanner.nextDouble();

                listaTrabajadores.add(new Jefe(nombre, apellido, dni, acciones, beneficio));

                break;

            case 2:
                System.out.println("Dime el nombre");
                nombre = scanner.next();
                System.out.println("Dime el apellido");
                apellido = scanner.next();
                System.out.println("Dime el dni");
                dni = scanner.next();
                System.out.println("Dime el sueldo");
                double sueldo = scanner.nextDouble();
                System.out.println("Dime el numero de pagas");
                int nPagas = scanner.nextInt();
                System.out.println("Dime si esta contratado");
                boolean contratado = scanner.hasNext();

                listaTrabajadores.add(new Asalariado(nombre, apellido, dni, sueldo, nPagas, contratado));

                break;

            case 3:
                System.out.println("Dime el nombre");
                nombre = scanner.next();
                System.out.println("Dime el apellido");
                apellido = scanner.next();
                System.out.println("Dime el dni");
                dni = scanner.next();
                System.out.println("Dime el sueldo");
                sueldo = scanner.nextDouble();
                System.out.println("Dime si esta contratado");
                contratado = scanner.hasNext();

                listaTrabajadores.add(new Autonomo(nombre, apellido, dni, sueldo, contratado));

                break;

            default:
                System.out.println("Opcion no permitida");
        }

    }


    public void listarTrabajadores(){
        System.out.println("Que quieres listar?");
        System.out.println("1. Jefes");
        System.out.println("2. Asalariados");
        System.out.println("3. Autonomos");
        System.out.println("4. Todos");

        int opcion = 0;
        opcion = scanner.nextInt();

        switch (opcion){
            case 1:
                for (Trabajador t : listaTrabajadores) {
                    if (t instanceof Jefe){
                        System.out.println("Nombre: " + t.getNombre() + " Apellido:" + t.getApellido() + " DNI:" + t.getDni() +
                                " Acciones: " + ((Jefe) t).getAcciones() + " Beneficio:" + ((Jefe) t).getBeneficio());
                    }
                }
                break;
            case 2:
                for (Trabajador t : listaTrabajadores){
                    if (t instanceof Asalariado){
                        System.out.println("Nombre: " + t.getNombre() + " Apellido:" + t.getApellido() + " DNI:" + t.getDni() +
                                " Salario Anual: " + ((Asalariado) t).getSueldo() + " Salario Mensual: " +
                                (((Asalariado) t).getSueldo() / ((Asalariado) t).getnPagas()) + " Numero de Pagas: " + ((Asalariado) t).getnPagas());
                    }
                }
                break;
            case 3:
                for (Trabajador t : listaTrabajadores) {
                    if (t instanceof Autonomo){
                        System.out.println("Nombre: " + t.getNombre() + "Apellido:" + t.getApellido() + "DNI:" + t.getDni() +
                                "Salario Anual: " + ((Autonomo) t).getSueldo());
                    }
                }
                break;
            default:
                System.out.println("Opcion no permitida");
        }
    }


    public void mostrarInformacion(String dni){

        if (listaTrabajadores.isEmpty()){
            System.out.println("No se ha registrado ningun trabajador");
        }else {
            buscarTrabajador(dni).mostrarDatos();
        }

    }


    private Trabajador buscarTrabajador(String dni){
        System.out.println("Dime el DNI del trabajador que quieras buscar");
        dni = scanner.next();
        for ( Trabajador t : listaTrabajadores ) {
            if (t.getDni().equalsIgnoreCase(dni)){
                return t;
            }
        }
        return null;
    }



    public ArrayList<Trabajador> getListaTrabajadores() {
        return listaTrabajadores;
    }

    public void setListaTrabajadores(ArrayList<Trabajador> listaTrabajadores) {
        this.listaTrabajadores = listaTrabajadores;
    }
}
