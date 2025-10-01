import java.util.Scanner;
public class Switch {
    static Scanner scanner = new Scanner (System.in);
    public static void main(String[] args){
        int numero = 6;
        //int numero = scanner.nextInt();
        switch (numero) {
            case 0:
                System.out.println("El valor es 0");
                break;
            case 5:
                System.out.println("El valor es 5");
                break;
            case 10:
                System.out.println("El valor es 10");
                break;
            default:
                System.out.println("El valor no esta contemplado");
                break;
        }
        System.out.println("Terminando bloque");
    }
}
