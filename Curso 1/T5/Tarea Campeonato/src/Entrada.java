import model.Coche;

import java.util.Scanner;

public class Entrada {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int kmEtapa = 1000;

        Coche cocheA = new Coche();
        Coche cocheB = new Coche("Ferrari","1234B",0);

        cocheA.mostrarDatos();
        cocheB.mostrarDatos();

        /*
        cocheA.acelerar((int) (Math.random() * 21) + 10);
        cocheB.acelerar((int) (Math.random() * 21) + 10);

        cocheA.mostrarDatos();
        cocheB.mostrarDatos();


        System.out.println("El coche ganador es: ");
        if (cocheA.getKm() > cocheB.getKm()){
            System.out.printf("Matricula: %s    Modelo: %s    Caballos: %d  Velocidad: %d   Kilometros: %.2f\n\n",
                    cocheA.getMatricula(), cocheA.getModelo(), cocheA.getCv(), cocheA.getVelocidad(), cocheA.getKm());
        } else if (cocheA.getKm() < cocheB.getKm()) {
            System.out.printf("Matricula: %s    Modelo: %s    Caballos: %d  Velocidad: %d   Kilometros: %.2f\n\n",
                    cocheB.getMatricula(), cocheB.getModelo(), cocheB.getCv(), cocheB.getVelocidad(), cocheB.getKm());
        }else {
            System.out.println("Empate");
        }
         */


        do {

            cocheA.acelerar((int) (Math.random() * 21) + 10);
            System.out.println(cocheA.getKm());
            cocheB.acelerar((int) (Math.random() * 21) + 10);
            System.out.println(cocheB.getKm());

        }while (kmEtapa > cocheA.getKm() && kmEtapa > cocheB.getKm());


        System.out.println("El coche ganador es");
        if (cocheA.getKm() > cocheB.getKm()){
            System.out.printf("Matricula: %s    Modelo: %s    Kilometros: %.2f\n\n",
                    cocheA.getMatricula(), cocheA.getModelo(), cocheA.getKm());
        } else if (cocheA.getKm() < cocheB.getKm()) {
            System.out.printf("Matricula: %s    Modelo: %s    Kilometros: %.2f\n\n",
                    cocheB.getMatricula(), cocheB.getModelo(), cocheB.getKm());
        }else {
            System.out.println("Empate");
        }

    }

}
