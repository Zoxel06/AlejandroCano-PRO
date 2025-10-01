package hotel_arraylist.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Hotel {
    static Scanner scanner = new Scanner(System.in);

    ArrayList<Reservas> listaReservas;

    public Hotel() {
        listaReservas = new ArrayList<>();
    }

    public void agregarPersona() {

        System.out.println("Dime tu habitacion");
        int habitacion = scanner.nextInt();
        System.out.println("Dime tu nombre");
        String nombre = scanner.next();
        System.out.println("Dime tu telefono");
        int telefono = scanner.nextInt();
        System.out.println("Dime tu dni");
        String dni = scanner.next();
        System.out.println("Dime las personas que sois");
        int personas = scanner.nextInt();

        Reservas reserva = new Reservas();
        reserva.habitacion = habitacion;
        reserva.nombre = nombre;
        reserva.telefono = telefono;
        reserva.dni = dni;
        reserva.personas = personas;

        listaReservas.add(reserva);

        System.out.println("Reserva añadida con exito");
    }


    public void cancelarReserva(){
        System.out.println("Dime tu dni");
        int dni = scanner.nextInt();


        for (int i = 0; i < listaReservas.size(); i++){
            if (listaReservas.isEmpty()){
                System.out.println("No hay ninguna reserva registrada");
            }else {
                if (listaReservas.get(i).dni.equals(dni)){
                    listaReservas.remove(i);
                }else {
                    System.out.println("No se ha encontrado ninguna reserva a nombre de este dni");
                }
            }
        }
    }



    public void mostrarReserva() {

        if (listaReservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            System.out.println("Reservas actuales:");
            for (Reservas item : listaReservas) {
                System.out.println(
                        "Habitación: " + item.habitacion +
                                ", Nombre: " + item.nombre +
                                ", Teléfono: " + item.telefono +
                                ", DNI: " + item.dni +
                                ", Personas: " + item.personas
                );
            }
        }
    }


    public void ordenarReservasCollections() {
        Collections.sort(listaReservas, new Comparator<Reservas>() {
            @Override
            public int compare(Reservas r1, Reservas r2) {
                // Compara las habitaciones (números enteros)
                return Integer.compare(r1.habitacion, r2.habitacion);
            }
        });
        System.out.println("Reservas ordenadas por número de habitación.");
    }


    public void ordenarReservasBurbuja() {
        // Usaremos el algoritmo de ordenación burbuja
        for (int i = 0; i < listaReservas.size(); i++) {
            for (int j = 0; j < listaReservas.size() - 1 - i; j++) {
                // Si el número de habitación de la reserva actual es mayor que el siguiente
                if (listaReservas.get(j).habitacion > listaReservas.get(j + 1).habitacion) {
                    // Intercambiamos las dos reservas
                    Reservas temp = listaReservas.get(j);
                    listaReservas.set(j, listaReservas.get(j + 1));
                    listaReservas.set(j + 1, temp);
                }
            }
        }
        System.out.println("Reservas ordenadas por número de habitación.");
    }


}
