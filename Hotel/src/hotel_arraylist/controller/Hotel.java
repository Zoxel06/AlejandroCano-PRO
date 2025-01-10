package hotel_arraylist.controller;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Hotel {

    private static Scanner scanner = new Scanner(System.in);

    private ArrayList<Object[]> listaReservas;

    public Hotel(){
        System.out.println("Iniciando hotel");
        listaReservas = new ArrayList<>();
    }

    public void registrarReserva(int habitación, String nombre, int telefono, String dni, int numeroPersonas){

        Object[] reserva = new Object[]{habitación, nombre, telefono, dni, numeroPersonas};

        if (!estaReservado(dni)){
            System.out.println("Reserva realizada correctamente");
            listaReservas.add(reserva);
        } else {
            System.out.println("Este dni ya ha registrado una reserva");
        }

    }


    private boolean estaReservado(String dni){
        for (int i = 0; i < listaReservas.size(); i++) {
            if (listaReservas.contains(dni)){
                return true;
            }
        }
        return false;
    }


    public void mostrarReservas(){
        if (listaReservas.isEmpty()){
            System.out.println("No hay ninguna reserva");
        } else {
            for ( Object[] item : listaReservas ) {
                System.out.println("Habitacion " + item[0] + " - " + " DNI: " + item[3]);
            }
        }
    }
}
