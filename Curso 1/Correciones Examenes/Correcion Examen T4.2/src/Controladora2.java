import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

public class Controladora2 {

    private ArrayList<Object[]> listaAlumnos;

    public Controladora2(){
        listaAlumnos = new ArrayList<>();
    }

    public void agregarAlumnos(String nombre){
        Object[] alumno = new Object[]{nombre,-1}; // como el -1 "no existe" lo detecta como no calificado (nota = no calificado)
        listaAlumnos.add(alumno);
    }

    public void calificar(){
        for (int i = 0; i < listaAlumnos.size(); i++) {
            int nota = (int) (Math.random()*10)+1;
            Object[] alumno = listaAlumnos.get(i);
            alumno[1] = nota;
        }
    }

    public void verSuspensos(){
        for (Object[] item:listaAlumnos) {
            if ((int) item[1] < 5){
                System.out.println("El alumo " + item[0] + " tiene la nota " + item[1]);
            }
        }
    }

    public void listarOrdenados(){
        listaAlumnos.sort(new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                if ((int)o1[1] > (int)o2[1]){
                    return 1;
                }else if ((int)o1[1] < (int)o2[1]){
                    return -1;
                }else{
                    return 0;
                }

            }
        });

    }

}
