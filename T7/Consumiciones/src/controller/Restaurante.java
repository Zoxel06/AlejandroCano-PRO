package controller;

import model.Cliente;
import model.Consumicion;

import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante {

    static Scanner scanner = new Scanner(System.in);
    private ArrayList<Cliente> listaClientes;
    private double caja;

    public Restaurante() {
        listaClientes = new ArrayList<>();
    }

    // admitir clientes. Tan solo se podrá admitir un cliente
    // si no existe uno con ese nombre


    public void agregarCliente(Cliente cliente) {
        // no pido datos aqui!!, esto se hace en la ENTRADA - UI -> el controller es para la logica
        // metera al cliente dentro de la lista
        if (estaCliente(cliente.getNombre()) != null) {
            System.out.println("Ya esta en el restaurante");
        } else {
            listaClientes.add(cliente);
            System.out.println("Bienvenido al restaurante");
        }
    }

    public void mostrarClientes() {
        for (Cliente cliente : listaClientes) {
            cliente.mostrarDatos();
        }
    }


    private Cliente estaCliente(String nombre) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombre)) {
                return cliente;
            }
        }
        return null;
    }

    public void agregarPedido(Consumicion consumicion, String nombre) {
        Cliente cliente = estaCliente(nombre);
        if (!estaConsumicion(consumicion.getNombre(), cliente)) {
            agregarConsumicion(cliente,consumicion);
        } else {
            System.out.println("Veo que ya tienes una consumicion de este tipo pedida, quieres repetirla?");
            String respuesta = scanner.next();
            if (respuesta.equalsIgnoreCase("si")) {
                System.out.println("Cuantas quieres?");
                int cantidad = scanner.nextInt();
                for (int i = 0; i < cantidad; i++) {
                    agregarConsumicion(cliente,consumicion);
                }
            } else {
                agregarConsumicion(cliente,consumicion);
            }
        }
    }

    private boolean estaConsumicion(String nombreConsumicion, Cliente cliente) {
        for (Consumicion consumicion : cliente.getConsumiciones()) {
            if (consumicion.getNombre().equalsIgnoreCase(nombreConsumicion)) {
                return true;
            }
        }
        return false;
    }

    public void agregarConsumicion(Cliente cliente, Consumicion consumicion){
        cliente.getConsumiciones().add(consumicion);
        cliente.incrementarFactura(consumicion.getPrecio());
    }

    public void mostrarDatosCliente(String nombre){
        Cliente cliente = estaCliente(nombre);
        if (cliente==null){
            System.out.println("No se encuentra este cliente en la lista");
        } else {
            System.out.println("Sus consumiciones son");
            for (Consumicion consumicion: cliente.getConsumiciones()) {
                consumicion.mostrarDatos();
            }
        }
    }

    public void pedirCuenta(String nombre){
        Cliente cliente = estaCliente(nombre);
        if (cliente==null){
            System.out.println("No se encuentra este cliente en la lista");
        } else {
            caja += cliente.getTotalFactura();
        }
    }

    public void mostrarCajaRestaurante(){
        System.out.println("caja = " + caja);
    }


}