import java.util.Scanner;

public class While {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ejemploWhile();
        //ejemploDoWhile();
    }


    public static void ejemploWhile() {

        int i = 10;

        while (i > 0) {
            System.out.println("El valor de i es :"+i);
            i--;
        }

    }

    public static void ejemploDoWhile() {
        int i = 7;
        do {
            System.out.println("Ejecuto");
            i++;
        } while (i < 10);
    }

}