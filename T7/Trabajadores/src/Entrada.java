import model.*;

import java.util.ArrayList;

public class Entrada {

    public static void main(String[] args) {

        ArrayList<Trabajador> listaTrabajadores = new ArrayList<>();

        Asalariado asalariado = new Asalariado("Alejandro","Cano",123,30000.0,0.02,12);
        Autonomo autonomo = new Autonomo("AlejandroA","CanoA",234,30000,6);
        Jefe jefe = new Jefe("Alejandro","Cano",345,60000);


        listaTrabajadores.add(asalariado);
        listaTrabajadores.add(autonomo);
        listaTrabajadores.add(jefe);


        for (Trabajador t : listaTrabajadores) {
            t.calcularSalarioMes();
            if (t instanceof Empleador){
                ((Empleador) t).realizarTrabajo(4);
            }

            if (t instanceof Sindicador){
                ((Sindicador) t).realizarHuelga();
            }
        }



    }

}
