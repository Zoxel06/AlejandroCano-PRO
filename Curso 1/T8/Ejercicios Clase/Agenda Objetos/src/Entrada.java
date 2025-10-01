import java.util.Scanner;

public class Entrada {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        OperacionesUsuario operacionesUsuario = new OperacionesUsuario();

        int opcion = 0;

        System.out.println("1.");
        System.out.println("2.");
        System.out.println("3.");
        System.out.println("4.");
        System.out.println("5.");
        System.out.println("6.");
        System.out.println("7.");
        System.out.println("Elije una opcion");

        do {
            opcion = scanner.nextInt();
            switch (opcion){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no permitida");
            }
        }while (opcion != 7);

        //operacionesUsuario.escribirUsuarioObjeto("src/recursos/agenda.obj");

    }

}
