package model;

public class TareasOcio extends Tareas{

    private String ubicacion;
    private double presupuesto;

    public TareasOcio(){

    }

    public TareasOcio(int id, String titulo, String descripcion, String fecha, Prioridad prioridad, String ubicacion, double presupuesto) {
        super(id, titulo, descripcion, fecha, prioridad);
        this.ubicacion = ubicacion;
        this.presupuesto = presupuesto;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", ubicacion='" + ubicacion + '\'' +
                ", presupuesto=" + presupuesto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }
}
