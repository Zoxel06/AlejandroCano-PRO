package EjerciciosSwitch;

import java.util.Scanner;

public class Ejercicio5 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce un código de producto");
        String codigo = scanner.next();

        switch (codigo) {
            case "P001":
                System.out.println("Producto: Lápiz, Precio: 0,50€");
                break;
            case "P002":
                System.out.println("Producto: Goma de borrar, Precio: 1,00€");
                break;
            case "P003":
                System.out.println("Producto: Bolígrafo, Precio: 1,50€");
                break;
            case "P004":
                System.out.println("Producto: Libro, Precio: 3,00€");
                break;
            case "P005":
                System.out.println("Producto: Cuaderno, Precio: 2,50€");
                break;
            default:
                System.out.println("Producto no existente");
        }

    }

}
