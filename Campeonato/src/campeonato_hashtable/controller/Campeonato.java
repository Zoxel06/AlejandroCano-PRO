package campeonato_hashtable.controller;

import java.util.Hashtable;
import java.util.Random;

public class Campeonato {
    private Hashtable<String, String[]> pilotos; // Hashtable para almacenar pilotos
    private int carrerasRestantes;
    private final int MAX_PILOTOS = 10;

    // Constructor
    public Campeonato() {
        pilotos = new Hashtable<>();
        carrerasRestantes = 7;  // Número total de carreras
    }

    public boolean agregarPiloto(String nombre, String bastidor) {
        if (pilotos.size() >= MAX_PILOTOS) {
            System.out.println("Error: No se pueden agregar más pilotos. El máximo es 10.");
            return false;
        }

        if (pilotos.containsKey(bastidor)) {
            System.out.println("Error: El bastidor ya está registrado.");
            return false;
        }

        pilotos.put(bastidor, new String[]{nombre, "0"});
        System.out.println("Piloto agregado con éxito.");
        return true;
    }

    public boolean actualizarPuntos(String bastidor) {
        if (carrerasRestantes <= 0) {
            System.out.println("Error: Ya no quedan carreras para repartir puntos.");
            return false;
        }

        Random random = new Random();
        int puntos = random.nextInt(12) + 1;
        for (String[] piloto : pilotos.values()) {
            if (Integer.parseInt(piloto[1]) == puntos) {  // Asegurar que no se repitan puntos
                return actualizarPuntos(bastidor);
            }
        }

        String[] piloto = pilotos.get(bastidor);
        if (piloto == null) {
            System.out.println("Error: Piloto no encontrado.");
            return false;
        }

        int puntosAcumulados = Integer.parseInt(piloto[1]);
        piloto[1] = String.valueOf(puntosAcumulados + puntos);
        carrerasRestantes--;  // Restar una carrera
        System.out.println("Puntos asignados al piloto " + piloto[0] + ": " + puntos + " puntos.");
        return true;
    }

    public void mostrarInformacionPiloto(String bastidor) {
        String[] piloto = pilotos.get(bastidor);
        if (piloto == null) {
            System.out.println("Error: Piloto no encontrado.");
            return;
        }
        System.out.println("Piloto: " + piloto[0] + ", Bastidor: " + bastidor + ", Puntos: " + piloto[1]);
    }

    public void mostrarClasificacion() {
        if (pilotos.isEmpty()) {
            System.out.println("No hay pilotos registrados.");
            return;
        }

        pilotos.values().stream()
                .sorted((p1, p2) -> Integer.compare(Integer.parseInt(p2[1]), Integer.parseInt(p1[1])))
                .forEach(piloto -> System.out.println("Piloto: " + piloto[0] + ", Puntos: " + piloto[1]));
    }

    public int getCarrerasRestantes() {
        return carrerasRestantes;
    }
}