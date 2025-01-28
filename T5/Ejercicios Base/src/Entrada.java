import java.util.Scanner;

public class Entrada {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Dime el nombre");
        String nombre = scanner.next();
        System.out.println("Dime el apellido");
        String apellido = scanner.next();
        System.out.println("Dime el dni");
        String dni = scanner.next();
        System.out.println("Dime la edad");
        int edad = scanner.nextInt();
        System.out.println("Dime el peso");
        double peso = scanner.nextDouble();
        System.out.println("Dime la altura");
        double altura = scanner.nextFloat();
        System.out.println("Dime el genero");
        String genero = scanner.next();

        Persona p1 = new Persona(nombre, apellido, dni, edad, peso, altura, genero);
        Persona p2 = new Persona("Alejandro", "Cano");
        Persona p3 = new Persona();


        p1.setEdad(20);
        p2.setEdad(20);
        p3.setEdad(20);

        p1.incrementarEdad();
        p2.incrementarEdad();
        p3.incrementarEdad();

        p1.mostrarDatos();
        p2.mostrarDatos();
        p3.mostrarDatos();

        double IMC = p1.calcularIMC();

        System.out.println("El IMC de la primera persona es:" + IMC);

        p1.determinarPeso();

        p1.determinarPesoGenero();

    }
}
