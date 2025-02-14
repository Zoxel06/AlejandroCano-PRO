import model.Asalariado;
import model.Autonomo;
import model.Trabajador;

import java.util.ArrayList;

public class Entrada {

    public static void main(String[] args) {
        ArrayList<Trabajador> listaTrabajadores = new ArrayList<>();

        Asalariado asalariado = new Asalariado("Alejandro","Cano",123,30000.0,0.02,12);
        Autonomo autonomo = new Autonomo("AlejandroA","CanoA",234,30000,6);

        listaTrabajadores.add(asalariado);
        listaTrabajadores.add(autonomo);

        for (Trabajador trabajador : listaTrabajadores) {
            trabajador.calcularSalarioMes();
        }



    }

}
