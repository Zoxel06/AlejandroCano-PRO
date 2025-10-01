import java.util.Scanner;
public class Ejercicio1 {
static Scanner scanner = new Scanner(System.in);

public static void main(String[] args){
    System.out.println("Dime tu nombre");
    String nombreo = scanner.next();
    System.out.println("Dime tu apellido");
    String apellido = scanner.next();
    System.out.println("¿De cuanto dinero dispones para hacer la compra?");
    int dinero = scanner.nextInt();
    System.out.println("¿Cuanto cuesta la play 5 que te quieres comprar?");
    int play = scanner.nextInt();
    System.out.println("¿Cuanto cuesta el iPhone 15 que te quieres comprar?");
    int iphone = scanner.nextInt();
    System.out.println("¿Cuanto es el iva actual?");
    int iva = scanner.nextInt();
    float playiva = play + (play*((float) iva /100));
    float iphoneiva = iphone + (iphone*((float) iva /100));
    boolean comprarplay = dinero>=playiva;
    boolean comprariphone = dinero>=iphoneiva;
    boolean comprarambos = dinero>=(playiva+iphoneiva);
    System.out.println("Te puedes comprar la play: "+comprarplay);
    System.out.println("Te puedes comprar el iphone: "+comprariphone);
    System.out.println("Te puedes comprar las dos cosas: "+comprarambos);
}
}
