package model;

public class TareasTrabajo extends Tareas{

    private String proyecto;

    public TareasTrabajo(){

    }

    public TareasTrabajo(int id, String titulo, String descripcion, String fecha, Prioridad prioridad, String proyecto) {
        super(id, titulo, descripcion, fecha, prioridad);
        this.proyecto = proyecto;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }
}
