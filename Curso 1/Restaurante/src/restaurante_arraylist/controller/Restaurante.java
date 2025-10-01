package restaurante_arraylist.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Restaurante {
    // Lista para almacenar las mesas ocupadas
    ArrayList<HashMap<String, Object>> mesasOcupadas = new ArrayList<>();
    int MAX_MESAS = 10;

    // Carta de productos del restaurante
    ArrayList<String[]> carta = new ArrayList<>();

    public Restaurante() {
        // Inicialización de la carta con productos y precios
        carta.add(new String[]{"Bocadillo", "5.50"});
        carta.add(new String[]{"Café", "2.00"});
        carta.add(new String[]{"Refresco", "2.50"});
        carta.add(new String[]{"Ensalada", "7.00"});
    }

    // Asignar una mesa a un cliente
    public boolean asignarMesa(int numeroMesa, String nombreCliente) {
        if (mesasOcupadas.size() >= MAX_MESAS) {
            System.out.println("Error: Todas las mesas están ocupadas.");
            return false;
        }

        for (HashMap<String, Object> mesa : mesasOcupadas) {
            if ((int) mesa.get("numeroMesa") == numeroMesa) {
                System.out.println("Error: La mesa ya está ocupada.");
                return false;
            }
        }

        HashMap<String, Object> nuevaMesa = new HashMap<>();
        nuevaMesa.put("numeroMesa", numeroMesa);
        nuevaMesa.put("nombreCliente", nombreCliente);
        nuevaMesa.put("consumiciones", new ArrayList<String>());
        nuevaMesa.put("totalPrecio", 0.0);

        mesasOcupadas.add(nuevaMesa);
        System.out.println("Mesa asignada con éxito.");
        return true;
    }

    // Liberar una mesa
    public boolean liberarMesa(int numeroMesa) {
        for (HashMap<String, Object> mesa : mesasOcupadas) {
            if ((int) mesa.get("numeroMesa") == numeroMesa) {
                System.out.println("Mesa liberada con éxito.");
                System.out.println("Resumen de consumiciones: " + mesa.get("consumiciones"));
                System.out.println("Total a pagar: " + mesa.get("totalPrecio") + " €");
                mesasOcupadas.remove(mesa);
                return true;
            }
        }
        System.out.println("Error: No se encontró la mesa especificada.");
        return false;
    }

    // Realizar un pedido en una mesa
    public boolean realizarPedido(int numeroMesa, String producto) {
        for (HashMap<String, Object> mesa : mesasOcupadas) {
            if ((int) mesa.get("numeroMesa") == numeroMesa) {
                for (String[] item : carta) {
                    if (item[0].equalsIgnoreCase(producto)) {  // Verificar si el producto está en la carta
                        ArrayList<String> consumiciones = (ArrayList<String>) mesa.get("consumiciones");
                        consumiciones.add(producto);  // Añadir producto a la lista de consumiciones

                        double totalPrecio = (double) mesa.get("totalPrecio") + Double.parseDouble(item[1]);
                        mesa.put("totalPrecio", totalPrecio);  // Actualizar el total de la mesa
                        System.out.println("Pedido añadido con éxito.");
                        return true;
                    }
                }
                System.out.println("Error: Producto no disponible en la carta.");
                return false;
            }
        }
        System.out.println("Error: Mesa no encontrada.");
        return false;
    }

    // Mostrar mesas ocupadas
    public void mostrarMesasOcupadas() {
        if (mesasOcupadas.isEmpty()) {
            System.out.println("No hay mesas ocupadas.");
            return;
        }

        System.out.println("Mesas ocupadas:");
        for (HashMap<String, Object> mesa : mesasOcupadas) {
            System.out.println("Mesa: " + mesa.get("numeroMesa") +
                    ", Cliente: " + mesa.get("nombreCliente") +
                    ", Consumiciones: " + mesa.get("consumiciones") +
                    ", Total: " + mesa.get("totalPrecio") + " €");
        }
    }

    // Mostrar mesas ordenadas por precio de mayor a menor
    public void mostrarMesasOrdenadasPorPrecio() {
        if (mesasOcupadas.isEmpty()) {
            System.out.println("No hay mesas ocupadas.");
            return;
        }

        mesasOcupadas.sort(Comparator.comparing((HashMap<String, Object> m) -> (double) m.get("totalPrecio")).reversed());
        System.out.println("Mesas ordenadas por precio total:");
        for (HashMap<String, Object> mesa : mesasOcupadas) {
            System.out.println("Mesa: " + mesa.get("numeroMesa") +
                    ", Cliente: " + mesa.get("nombreCliente") +
                    ", Consumiciones: " + mesa.get("consumiciones") +
                    ", Total: " + mesa.get("totalPrecio") + " €");
        }
    }
}