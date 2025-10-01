import model.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        PlataformaJuegos plataformaJuegos = new PlataformaJuegos();

        Videojuego videojuego1 = new VideojuegoAccion("A1", "descripcion1", 2015, 30.0, ClasificacionEdad.PEGI15, 2, true);
        Videojuego videojuego2 = new VideojuegoAccion("A2", "descripcion2", 2019, 45.0, ClasificacionEdad.PEGI18, 4, false);
        Videojuego videojuego3 = new VideojuegoEstrategia("E1", "descripcion 3", 2020, 25.0, ClasificacionEdad.PEGI15, 3, 20);
        Videojuego videojuego4 = new VideojuegoEstrategia("E2", "descripcion4", 2020, 10.0, ClasificacionEdad.PEGI9, 1, 15);
        Videojuego videojuego5 = new VideojuegoRPG("R1", "descripcion5", 2016, 30.0, ClasificacionEdad.PEGI9, 20, false);
        Videojuego videojuego6 = new VideojuegoRPG("R2", "descripcion6", 2024, 60.0, ClasificacionEdad.PEGI18, 30, true);

        plataformaJuegos.aniadirJuego(videojuego1);
        plataformaJuegos.aniadirJuego(videojuego2);
        plataformaJuegos.aniadirJuego(videojuego3);
        plataformaJuegos.aniadirJuego(videojuego4);
        plataformaJuegos.aniadirJuego(videojuego5);
        plataformaJuegos.aniadirJuego(videojuego6);

        int opcion = 0;

        do {
            System.out.println("Elije una opcion");
            System.out.println("1. Mostrar juegos disponibles");
            System.out.println("2. Filtrar juegos por tipo");
            System.out.println("3. Filtrar juegos por clasificacion de edad");
            System.out.println("4. Ver detalles de un juego");
            System.out.println("5. Calcular tiempo de descarga de un juego");
            System.out.println("6. Calcular precio total de todos los juegos");
            System.out.println("7. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    int numero = 1;
                    for (Videojuego item : plataformaJuegos.getListaVideojuegos()) {
                        System.out.println("Videojuego " + numero);
                        System.out.println(item.toString());
                        numero += 1;
                    }
                    break;
                case 2:
                    int opcionJuego = 0;
                    System.out.println("Que tipo de juegos quieres ver?");
                    System.out.println("1-Accion");
                    System.out.println("2-Estrategia");
                    System.out.println("3-RPG");
                    opcionJuego = scanner.nextInt();

                    switch (opcionJuego){
                        case 1:
                            for (Videojuego item : plataformaJuegos.getListaVideojuegos()) {
                                if (item instanceof VideojuegoAccion){
                                    System.out.println(item.toString());
                                }
                            }
                            break;
                        case 2:
                            for (Videojuego item : plataformaJuegos.getListaVideojuegos()) {
                                if (item instanceof VideojuegoEstrategia){
                                    System.out.println(item.toString());
                                }
                            }
                            break;
                        case 3:
                            for (Videojuego item : plataformaJuegos.getListaVideojuegos()) {
                                if (item instanceof VideojuegoRPG){
                                    System.out.println(item.toString());
                                }
                            }
                            break;
                        default:
                            System.out.println("Opcion no permitida");
                            break;
                    }
                    break;
                case 3:
                    plataformaJuegos.filtrarClasificacion();
                    break;
                case 4:
                    int posicion = 0;
                    System.out.println("Selecciona la posicion del juego del cual quieres ver los detalles (1-6)");
                    posicion = scanner.nextInt();

                    int i = 0;
                    for (Videojuego item : plataformaJuegos.getListaVideojuegos()) {
                        i++;
                        if (i == posicion) {
                            System.out.println(item.toString());
                        }
                    }

                    break;
                case 5:
                    System.out.println("De que juego quieres calcular el tiempo de descarga:");
                    break;
                case 6:
                    double precioTotal = 0;
                    for (Videojuego item : plataformaJuegos.getListaVideojuegos()) {
                        precioTotal += item.calcularPrecioFinal();
                    }
                    System.out.println("El precio total de los juegos es de " + precioTotal);
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no permitida");
            }

        } while (opcion != 7);
    }

}
