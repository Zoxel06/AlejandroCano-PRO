import controller.Constructora;
import model.Terreno;
import utils.*;

import java.util.Scanner;

public class Entrada {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

Terreno terreno = null;

        int opcion = 0;

        do {
            System.out.println("1. Crear terreno");
            System.out.println("2. Crear casa");
            System.out.println("3. Mostrar datos del terreno");
            System.out.println("4. Crear habitacion");
            System.out.println("5. Crear piscina");
            System.out.println("6. Crear anexo");
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

                    Orientacion orientacion = null;

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
                    terreno = new Terreno(m2Terreno,orientacion,valoracion);
                    break;
                case 2:
                        System.out.println("Dime los m2 de la casa");
                        double m2Casa = sc.nextDouble();
                        try {
                            terreno.construirCasa(m2Casa);
                            if (terreno == null) {
                                throw new NullPointerException();
                            }
                        } catch (NullPointerException e) {
                            System.out.println("Para constuir una casa tienes que haber creado el terreno antes");
                        }
                    break;
                case 3:
                    terreno.mostrarDatosTerreno();
                    break;
                case 4:
                    System.out.println("De cuantos metros cuadrados va a ser la habitacion?");
                    double m2Habitacion = sc.nextDouble();
                    try {
                        terreno.getCasa().construirHabitacion(m2Habitacion);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 5:
                        terreno.getCasa().construirPiscina();
                    break;
                case 6:
                    System.out.println("De cuantos metros cuadrados va a ser el anexo?");
                    double m2Anexo = sc.nextDouble();
                    terreno.getCasa().construirAnexo(m2Anexo);
                    break;
                case 7:
                    terreno.mostrarDatosCasa();
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no permitida");
            }

        } while (opcion != 8);

    }

}
