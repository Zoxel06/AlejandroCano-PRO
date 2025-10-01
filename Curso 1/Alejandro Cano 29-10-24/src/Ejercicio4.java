import java.util.Scanner;

public class Ejercicio4 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int numero = 0;
        int base2 = 0;
        int base8 = 0;
        int base16 = 0;
        int opcion = 0;


        do {
            System.out.println("Introduce un numero en base decimal");
            numero = scanner.nextInt();
            if (numero > -1) {
                System.out.println("1. Base 2");
                System.out.println("2. Base 8");
                System.out.println("3. Base 16");
                System.out.println("4. Salir");
                System.out.println("Elige una opcion");

                opcion = scanner.nextInt();


                switch (opcion) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Base no contemplada");
                }
            } else {
                System.out.println("No se puede realizar la operacion con numeros negativos");
            }

        } while (opcion != 4);
    }

    public static void pasoBase2(int numero){
        int resultado = 0;
        int resto = 0;

        String numeroResultante = "";
        if (numero==1){
            numeroResultante = "1";
        } else {
            resultado = numero;
            do {
                resultado = resultado / 2;
                resto = resultado%2;
                numeroResultante =  String.valueOf(resto) + numeroResultante;
            } while (resultado > 1);
        }
    }
}
