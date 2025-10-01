import java.util.Scanner;

public class Entrada {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        OperacionesUsuario operacionesUsuario = new OperacionesUsuario();

        /*System.out.println("Dime el nombre");
        String nombre = scanner.next();
        System.out.println("Dime el apellido");
        String apellido = scanner.next();
        System.out.println("Dime el correo");
        String correo = scanner.next();
        System.out.println("Dime el telefono");
        int telefono = scanner.nextInt();

        System.out.println("Son correctos los datos?");
        System.out.println("nombre = " + nombre);
        System.out.println("apellido = " + apellido);
        System.out.println("correo = " + correo);
        System.out.println("telefono = " + telefono);
        String respuesta = scanner.next();

        if (respuesta.equalsIgnoreCase("si")) {
            operacionesUsuario.escribirUsuario("src/recursos/agenda.txt",new Usuario(nombre,apellido,correo,telefono));
        } else {
            System.out.println("Repitelos bien espabilao");
        }*/

        //operacionesUsuario.lecturaUsuario("src/recursos/agenda.txt");
        operacionesUsuario.escribirUsuarioObjeto("src/recursos/agenda.obj",new Usuario("Alejandro","Cano","correo@aaa",123456));
        operacionesUsuario.leerUsuarioObjeto("src/recursos/agenda.obj");
    }

}
