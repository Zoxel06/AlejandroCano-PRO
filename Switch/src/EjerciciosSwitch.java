import java.util.Scanner;

public class EjerciciosSwitch {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //ejercicio1();
        //ejercicio2();
        //ejercicio3();
        //ejercicio4();
        //ejercicio5();
        //ejercicio6();
        //ejercicio7();
        //ejercicio8();
        //ejercicio9();
        ejercicio10();
    }

    public static void ejercicio1() {
        System.out.println("Dime un numero del 1 al 7");
        int numero = scanner.nextInt();
        switch (numero) {
            case 1:
                System.out.println("Lunes");
                break;
            case 2:
                System.out.println("Martes");
                break;
            case 3:
                System.out.println("Miercoles");
                break;
            case 4:
                System.out.println("Jueves");
                break;
            case 5:
                System.out.println("Viernes");
                break;
            case 6:
                System.out.println("Sabado");
                break;
            case 7:
                System.out.println("Domingo");
                break;
            default:
                System.out.println("No es un dia de la semana");
        }
        System.out.println("Terminando bloque");
    }

    public static void ejercicio2() {
        System.out.println("Dime una letra");
        char letra = scanner.next().charAt(0);
        switch (letra) {
            case 'A' | 'E' | 'I' | 'O' | 'U':
                System.out.println("Vocal");
                break;
            default:
                System.out.println("Consonante");
        }
        System.out.println("Terminando bloque");
    }

    public static void ejercicio3() {
        System.out.println("Dime un numero");
        int numero = scanner.nextInt();
        int resto = numero % 2;
        switch (resto) {
            case 0:
                System.out.println("Par");
                break;
            default:
                System.out.println("Impar");
        }
        System.out.println("Terminando bloque");
    }

    public static void ejercicio4() {
        System.out.println("Ingresa un mes a modo de numero");
        int mes = scanner.nextInt();
        switch (mes) {
            case 1, 3, 5, 7, 8, 10, 12:
                System.out.println("31 dias");
                break;
            case 4, 6, 9, 11:
                System.out.println("30 dias");
                break;
            case 2:
                System.out.println("28 dias");
                break;
            default:
                System.out.println("No es un mes del año");
        }
        System.out.println("Terminando bloque");
    }

    public static void ejercicio5() {
        System.out.println("Ingresa un mes a modo de numero");
        String mes = scanner.next();
        switch (mes.toLowerCase()) {
            case "enero":
            case "marzo":
            case "mayo":
            case "julio":
            case "agosto":
            case "octubre":
            case "diciembre":
                System.out.println("31 dias");
                break;
            case "abril":
            case "junio":
            case "septiembre":
            case "noviembre":
                System.out.println("30 dias");
                break;
            case "febrero":
                System.out.println("29 dias");
                break;
            default:
                System.out.println("No es un mes del año");
        }
        System.out.println("Terminando bloque");
    }

    public static void ejercicio6() {
        System.out.println("1. Añadir\n2. Listar\n3. Buscar\n4. Modificar\n5. Salir");
        int numero = scanner.nextInt();
        switch (numero) {
            case 1:
                System.out.println("Opcion 1 seleccionada");
                break;
            case 2:
                System.out.println("Opcion 2 seleccionada");
                break;
            case 3:
                System.out.println("Opcion 3 seleccionada");
                break;
            case 4:
                System.out.println("Opcion 4 seleccionada");
                break;
            case 5:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("No es una opcion de las mostradas");
        }
        System.out.println("Terminando bloque");
    }

    public static void ejercicio7() {
        System.out.println("Introduce una nota del 1 al 5");
        int nota = scanner.nextInt();
        switch (nota) {
            case 1, 2:
                System.out.println("Suspenso");
                break;
            case 3:
                System.out.println("Aprobado");
                break;
            case 4:
                System.out.println("Notable");
                break;
            case 5:
                System.out.println("Sobresaliente");
                break;
            default:
                System.out.println("La nota introducida no se encuentra entre los numeros pedidos");
        }
        System.out.println("Terminando bloque");
    }

    public static void ejercicio8() {
        System.out.println("Ingresa un caracter que represente una operacion matematica");
        char operacion = scanner.next().charAt(0);
        System.out.println("Ahora introduce los dos numeros que quieras operar");
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        switch (operacion) {
            case '+':
                System.out.println(n1 + n2);
                break;
            case '-':
                System.out.println(n1 - n2);
                break;
            case '*':
                System.out.println(n1 * n2);
                break;
            case '/':
                System.out.println((float) n1 / n2);
                break;
            default:
                System.out.println("El caracter introducido no es valido");
        }
        System.out.println("Terminando bloque");
    }

    public static void ejercicio9() {
        System.out.println("Ingresa un numero del 1 al 12");
        int mes = scanner.nextInt();
        switch (mes) {
            case 12, 1, 2, 3:
                System.out.println("Invierno");
                break;
            case 4, 5:
                System.out.println("Primavera");
                break;
            case 6, 7, 8:
                System.out.println("Verano");
                break;
            case 9, 10, 11:
                System.out.println("Otoño");
                break;
            default:
                System.out.println("El numero introducido no es un mes");
        }
        System.out.println("Terminando bloque");
    }

    public static void ejercicio10() {
        System.out.println("Ingresa el codigo del producto");
        int codigo = scanner.nextInt();
        switch (codigo) {
            case 1:
                System.out.println("Play - 300€");
                break;
            case 2:
                System.out.println("Movil - 199€");
                break;
            case 3:
                System.out.println("Portatil - 400€");
                break;
            case 4:
                System.out.println("Tele - 899€");
                break;
            case 5:
                System.out.println("XBox - 350€");
                break;
            case 6:
                System.out.println("Silla - 20€");
                break;
            case 7:
                System.out.println("Sofa - 95€");
                break;
            case 8:
                System.out.println("Mesa - 60€");
                break;
            case 9:
                System.out.println("Lampara - 49€");
                break;
            default:
                System.out.println("No tenemos ningun producto registrado con ese codigo");
        }
        System.out.println("Terminando bloque");
    }
}
