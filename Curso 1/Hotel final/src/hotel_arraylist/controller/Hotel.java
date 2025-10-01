package hotel_arraylist.controller;

import java.util.ArrayList;
import java.util.Comparator;

public class Hotel {
    ArrayList<Reserva> reservas = new ArrayList<>();
    int MAX_HABITACIONES = 20;

    public boolean registrarReserva(int numeroHabitacion, String nombreHuesped, String telefonoHuesped, String dniHuesped, int numeroPersonas) {
        if (reservas.size() >= MAX_HABITACIONES) {
            System.out.println("Error: El hotel está lleno.");
            return false;
        }

        for (Reserva reserva : reservas) {
            if (reserva.dniHuesped.equals(dniHuesped)) {
                System.out.println("Error: El huésped ya tiene una reserva activa.");
                return false;
            }
        }

        Reserva nuevaReserva = new Reserva(numeroHabitacion, nombreHuesped, telefonoHuesped, dniHuesped, numeroPersonas);
        reservas.add(nuevaReserva);
        System.out.println("Reserva registrada con éxito.");
        return true;
    }

    public boolean cancelarReserva(String dniHuesped) {
        for (Reserva reserva : reservas) {
            if (reserva.dniHuesped.equals(dniHuesped)) {
                reservas.remove(reserva);
                System.out.println("Reserva cancelada con éxito.");
                return true;
            }
        }
        System.out.println("Error: No se encontró una reserva con ese DNI.");
        return false;
    }

    public void mostrarReservasActuales() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas activas.");
            return;
        }

        System.out.println("Reservas actuales:");
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    public void mostrarReservasOrdenadas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas activas.");
            return;
        }

        reservas.sort(Comparator.comparing(r -> r.nombreHuesped));
        System.out.println("Reservas ordenadas por nombre del huésped:");
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }
}

class Reserva {
    int numeroHabitacion;
    String nombreHuesped;
    String telefonoHuesped;
    String dniHuesped;
    int numeroPersonas;

    public Reserva(int numeroHabitacion, String nombreHuesped, String telefonoHuesped, String dniHuesped, int numeroPersonas) {
        numeroHabitacion = numeroHabitacion;
        nombreHuesped = nombreHuesped;
        telefonoHuesped = telefonoHuesped;
        dniHuesped = dniHuesped;
        numeroPersonas = numeroPersonas;
    }

    @Override
    public String toString() {
        return "Habitación: " + numeroHabitacion + ", Nombre: " + nombreHuesped + ", Teléfono: " + telefonoHuesped +
                ", DNI: " + dniHuesped + ", Personas: " + numeroPersonas;
    }
}
