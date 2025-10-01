import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        ArrayList<Vehiculo> stock = new ArrayList<>();

        double caja = 0;

        //Crear empresa
        Empresa empresa = new Empresa();

        //Añadir vehiculos
        Vehiculo vehiculo1 = new Coche(12345, 50000, "Coche", "1", "Seminuevo", 200, 300);
        stock.add(vehiculo1);
        Vehiculo vehiculo2 = new Moto(23456, 30000, "Moto", "1", "Usado", "Baja", 50);
        stock.add(vehiculo2);
        Vehiculo vehiculo3 = new Patinete(34567, 1000, "Patinete", "1", "Nuevo", "Autonomia");
        stock.add(vehiculo3);
        Vehiculo vehiculo4 = new Bici(45678, 300, "Bici", "1", "Nuevo", "mtb");
        stock.add(vehiculo4);


        for (int i = 0; i < stock.size(); i++) {
            if (stock.get(i).getEstado().equalsIgnoreCase("nuevo")) {
                stock.get(i).setPrecioVenta(stock.get(i).getPrecioVenta() + (stock.get(i).getPrecioVenta() * 0.10));
            } else if (stock.get(i).getEstado().equalsIgnoreCase("seminuevo")) {
                stock.get(i).setPrecioVenta(stock.get(i).getPrecioVenta() + (stock.get(i).getPrecioVenta() * 0.05));
            }
        }


        //Mostrar informacion
        for (int i = 0; i < stock.size(); i++) {
            System.out.println("Vehiculo " + (i + 1) + ":");
            System.out.println("Numero de serie: " + stock.get(i).getNumSerie() + "  Marca: " + stock.get(i).getMarca() +
                    "  Modelo: " + stock.get(i).getModelo() + "  Precio: " + stock.get(i).getPrecioVenta() +
                    "  Estado: " + stock.get(i).getEstado());
        }

        //Vender vehiculos
        for (int i = 0; i < stock.size(); i++) {
            if (stock.contains(vehiculo1)) {
                caja += vehiculo1.getPrecioVenta();
                stock.remove(vehiculo1);
            }
            if (stock.contains(vehiculo2)) {
                caja += vehiculo2.getPrecioVenta();
                stock.remove(vehiculo2);
            }
            if (stock.contains(vehiculo3)) {
                caja += vehiculo3.getPrecioVenta();
                stock.remove(vehiculo3);
            }

        }

        //Mostrar caja
        System.out.println();
        System.out.println("La caja de la tienda es " + caja + "$");

    }

}
