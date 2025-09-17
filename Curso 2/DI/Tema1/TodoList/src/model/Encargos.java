package model;

public class Encargos {

    private String descripcion;
    private int id;
    private boolean completada;

    public Encargos() {
    }

    public Encargos(String descripcion, int id, boolean completa) {
        this.descripcion = descripcion;
        this.id = id;
        this.completada = completada;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    @Override
    public String toString() {
        return "Encargos{" +
                "descripcion='" + descripcion + '\'' +
                ", id='" + id + '\'' +
                ", completa=" + completada +
                '}';
    }
}
