import model1.Alumno;
import model1.Asignatura;
import model1.Profesor;

import java.util.ArrayList;

public class Entrada1 {

    public static void main(String[] args) {

        ArrayList<Asignatura> listaAsignaturas = new ArrayList<>();

        listaAsignaturas.add(new Asignatura(1));
        listaAsignaturas.add(new Asignatura(2));
        listaAsignaturas.add(new Asignatura(3));

        Asignatura programacion = new Asignatura(1);
        Asignatura sistemas = new Asignatura(2);
        Asignatura lenguaje = new Asignatura(3);

        Alumno alumno1 = new Alumno(1, 2, 3);
        Alumno alumno2 = new Alumno(1, 2, 3);
        Alumno alumno3 = new Alumno(1, 2, 3);
        Alumno alumno4 = new Alumno(1, 2, 3);
        Alumno alumno5 = new Alumno(1, 2, 3);

        Profesor profesor1 = new Profesor();

        profesor1.ponerNotas(alumno1);

        System.out.println("La nota de la asignatura PRO es: " + alumno1.getAsignatura1().getCalificacion());
        System.out.println("La nota de la asignatura SIS es: " + alumno1.getAsignatura2().getCalificacion());
        System.out.println("La nota de la asignatura LM es: " + alumno1.getAsignatura3().getCalificacion());

        profesor1.calcularMedia(alumno1);

        System.out.println("La media del alumno es: " + profesor1.calcularMedia(alumno1));



        System.out.println("La nota de la asignatura PRO es: " + alumno2.getAsignatura1().getCalificacion());
        System.out.println("La nota de la asignatura SIS es: " + alumno2.getAsignatura2().getCalificacion());
        System.out.println("La nota de la asignatura LM es: " + alumno2.getAsignatura3().getCalificacion());

        profesor1.calcularMedia(alumno2);

        System.out.println("La media del alumno es: " + profesor1.calcularMedia(alumno2));

    }

}
