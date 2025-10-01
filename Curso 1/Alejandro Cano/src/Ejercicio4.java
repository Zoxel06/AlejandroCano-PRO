import java.util.Scanner;
public class Ejercicio4 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Introduce tu nombre");
        String nombre = scanner.next();
        System.out.println("Introduce tu apellido");
        String apellido = scanner.next();
        System.out.println("¿Cuanto sueldo esperas recibir?");
        int sueldo = scanner.nextInt();
        System.out.println("Introduce tu edad");
        int edad = scanner.nextInt();
        System.out.println("Introduce el dia de tu cumpleaños");
        int cumpleaños = scanner.nextInt();
        System.out.println("Introduce si tienes o no carne de conducir");
        String carne = scanner.next();
        boolean valido1 = (edad < 50) && (sueldo < 40000) && (carne.equals("si"));
        boolean valido2 = (edad>45) && (sueldo<20000) && (cumpleaños%2==0);
        boolean valido = valido1 || valido2;
        System.out.printf("Con los datos introducidos, el candidato cuyo nombre es %s %s " +
                "tiene como resolución: %b",nombre,apellido,valido);
    }

}
