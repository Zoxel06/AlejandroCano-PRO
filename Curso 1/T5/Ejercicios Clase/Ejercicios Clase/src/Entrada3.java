import model3.Circulo;
import model3.Cuadrado;
import model3.Triangulo;

import java.util.Scanner;

public class Entrada3 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion = 0;

        do {

            System.out.println("1. Trabajar con triangulos");
            System.out.println("2. Trabajar con cicrulos");
            System.out.println("3. Trabajar con cuadrados");
            System.out.println("4. Salir");
            System.out.println("Elije una opcion");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    int eleccion1 = 0;
                    System.out.println("Introduce base");
                    int baseTriangulo = scanner.nextInt();
                    System.out.println("Introduce altura");
                    int alturaTriangulo = scanner.nextInt();

                    Triangulo triangulo = new Triangulo(baseTriangulo, alturaTriangulo);

                    do {
                        System.out.println("Que operacion quieres hacer?");
                        System.out.println("1. Calcular area");
                        System.out.println("2. Mostrar datos");
                        System.out.println("3. Salir");
                        eleccion1 = scanner.nextInt();

                        switch (eleccion1) {
                            case 1:
                                triangulo.calcularAreaTriangulo();
                                System.out.println("El area del triangulo es:" + triangulo.calcularAreaTriangulo());
                                break;

                            case 2:
                                System.out.println("La base del triangulo es:" + baseTriangulo);
                                System.out.println("El altura del triangulo es:" + alturaTriangulo);
                                System.out.println("El area del triangulo es:" + triangulo.calcularAreaTriangulo());
                                break;
                            case 3:
                                System.out.println("Saliendo...");
                                break;
                            default:
                                System.out.println("Opcion no contemplada, intentalo de nuevo");
                        }

                    } while (eleccion1 != 3);

                    break;

                case 2:
                    int eleccion3 = 0;
                    System.out.println("Introduce radio");
                    int radioCicrulo = scanner.nextInt();
                    Circulo circulo = new Circulo(radioCicrulo);

                    do {
                        System.out.println("Que operacion quieres hacer?");
                        System.out.println("1. Calcular area");
                        System.out.println("2. Calcular diametro");
                        System.out.println("3. Mostrar datos");
                        System.out.println("4. Salir");
                        eleccion3 = scanner.nextInt();

                        switch (eleccion3) {
                            case 1:
                                circulo.calcularAreaCirculo();
                                System.out.println("El area del cuadrado es:" + circulo.calcularAreaCirculo());
                                break;

                            case 2:
                                circulo.calcularDiametroCirculo();
                                System.out.println("El perimetro del cuadrado es:" + circulo.calcularDiametroCirculo());
                                break;
                            case 3:
                                System.out.println("El radio del circulo es:" + radioCicrulo);
                                System.out.println("El area del circulo es:" + circulo.calcularAreaCirculo());
                                System.out.println("El perimetro del circulo es:" + circulo.calcularDiametroCirculo());
                                break;

                            case 4:
                                System.out.println("Saliendo...");
                                break;
                            default:
                                System.out.println("Opcion no contemplada, intentalo de nuevo");
                        }

                    } while (eleccion3 != 4);

                    break;

                case 3:

                    int eleccion2 = 0;
                    System.out.println("Introduce base");
                    int baseCuadrado = scanner.nextInt();
                    System.out.println("Introduce altura");
                    int alturaCuadrado = scanner.nextInt();

                    Cuadrado cuadrado = new Cuadrado(baseCuadrado, alturaCuadrado);

                    do {
                        System.out.println("Que operacion quieres hacer?");
                        System.out.println("1. Calcular area");
                        System.out.println("2. Calcular perimetro");
                        System.out.println("3. Mostrar datos");
                        System.out.println("4. Salir");
                        eleccion2 = scanner.nextInt();

                        switch (eleccion2) {
                            case 1:
                                cuadrado.calcularAreaCuadrado();
                                System.out.println("El area del cuadrado es:" + cuadrado.calcularAreaCuadrado());
                                break;

                            case 2:
                                cuadrado.calcularPerimetroCuadrado();
                                System.out.println("El perimetro del cuadrado es:" + cuadrado.calcularPerimetroCuadrado());
                                break;
                            case 3:
                                System.out.println("La base del cuadrado es:" + baseCuadrado);
                                System.out.println("El altura del cuadrado es:" + alturaCuadrado);
                                System.out.println("El area del cuadrado es:" + cuadrado.calcularAreaCuadrado());
                                System.out.println("El perimetro del cuadrado es:" + cuadrado.calcularPerimetroCuadrado());
                                break;

                            case 4:
                                System.out.println("Saliendo...");
                                break;
                            default:
                                System.out.println("Opcion no contemplada, intentalo de nuevo");
                        }

                    } while (eleccion2 != 4);

                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no contemplada, intentalo de nuevo");
            }

        } while (opcion != 4);

    }

}
