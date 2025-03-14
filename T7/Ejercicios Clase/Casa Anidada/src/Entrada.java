import controller.Constructora;
import model.Terreno;
import utils.Orientacion;

import java.util.Scanner;

public class Entrada {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Constructora constructora = new Constructora();

        int opcion = 0;

        do {
            System.out.println("1. Crear terreno");
            System.out.println("2. Crear casa");
            System.out.println("3. Crear habitacion");
            System.out.println("4. Crear piscina");
            System.out.println("5. Crear anexo");
            System.out.println("6. Crear habitacion con mas m2 que la casa");
            System.out.println("7. Mostrar datos de la casa");
            System.out.println("8. Salir");
            System.out.println("Elije una opcion");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Dime los m2 del terreno");
                    double m2Terreno = sc.nextDouble();
                    System.out.println("Dime el precio del terreno");
                    double valoracion = sc.nextDouble();
                    System.out.println("Dime la orientacion del terreno");
                    System.out.println("1. Norte");
                    System.out.println("2. Sur");
                    System.out.println("3. Este");
                    System.out.println("4. Oeste");

                    int tipoOrientacion = sc.nextInt();

                    Orientacion orientacion;

                    switch (tipoOrientacion){
                        case 1:
                            orientacion = Orientacion.NORTE;
                            break;
                        case 2:
                            orientacion = Orientacion.SUR;
                            break;
                        case 3:
                            orientacion = Orientacion.ESTE;
                            break;
                        case 4:
                            orientacion = Orientacion.OESTE;
                            break;
                        default:
                            System.out.println("Opcion no permitida");
                    }
                    Terreno terreno = new Terreno();
                    break;
                case 2:
                    System.out.println("Dime los m2 de la casa");
                    double m2Casa = sc.nextDouble();
                    System.out.println("Va a tener piscina?");
                    String respueta = sc.next();
                    if (respueta.equalsIgnoreCase("si")) {
                        boolean piscina = true;
                    } else if (respueta.equalsIgnoreCase("no")) {
                        boolean piscina = false;
                    }
                    constructora.construirCasa(m2Casa);
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

                    break;
                case 8:

                    break;
                default:
                    System.out.println("Opcion no permitida");
            }

        } while (opcion != 8);

    }

}
