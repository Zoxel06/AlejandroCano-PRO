import java.util.Scanner;

public class EntradaTeclado {

    static Scanner lectorTeclado = new Scanner(System.in);
    public static void main(String[] args) {
        String nombre;
        int edad;
        float altura;
        boolean experiencia;

        // String nombre; --> no tiene valor asignado por lo que su valor es "null" (nulo)
        System.out.println("Por favor introduce tu nombre");
        nombre = lectorTeclado.next();
        System.out.println("Mi nombre es "+nombre);
        System.out.println("Introduce tambien tu edad");
        edad = lectorTeclado.nextInt();
        System.out.println("Tengo "+edad+" años");
        System.out.println("Genial, ahora introduce tu altura");
        altura = lectorTeclado.nextFloat();
        System.out.println("Mido "+altura);
        System.out.println("Por ultimo, dime si tienes experiencia trabajando");
        experiencia = lectorTeclado.nextBoolean();
        System.out.println(experiencia);
        System.out.printf("Me llamo %s, tengo %d años y mido %.2fm, y %b tienes experiencia\n", nombre, edad, altura, experiencia);

    }
}
