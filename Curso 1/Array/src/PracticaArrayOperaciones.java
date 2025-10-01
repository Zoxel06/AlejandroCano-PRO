import java.util.Scanner;

public class PracticaArrayOperaciones {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Object[] cosas = {5,"dam",true,'a',5.9,7};
        /* for (int i = 0; i< cosas.length; i++){

            if (cosas[i] instanceof String){
                System.out.println(cosas[i]);
                System.out.println("La longitud de la palabra es " + ((String) cosas[i]).length());
            }
        } */


        //ESTÁ MAL, REVISAR EN EL GITHUB DE CLASE

        int[] numeros = {1,2,3,4,124,512,1234,1234,222,0,35478};
        System.out.println("Introduce el numero que quieres buscar");

        int numeroUsuario;
        boolean respuesta;

        do {
            numeroUsuario = scanner.nextInt();
            respuesta = estaNumero(numeroUsuario,numeros);
        }while (!respuesta);

        if (!estaNumero(numeroUsuario,numeros)){
            System.out.println("El numero no esta");
        }

    }

    public static boolean estaNumero(int numeroBuscar, int[] conjunto) {

        for (int i = 0; i < conjunto.length; i++) {
            if (i == numeroBuscar){
                System.out.println("Numero encontrado");
                return true;
            }
        }

        return false;
    }
}
