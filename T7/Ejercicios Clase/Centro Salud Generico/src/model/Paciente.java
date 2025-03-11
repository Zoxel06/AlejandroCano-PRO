package model;

public class Paciente {
    private String nombre;
    private String apellido;
    private int nss;
    private String dolencia;

    public Paciente() {
    }

    public Paciente(String nombre, String apellido, int nss, String dolencia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nss = nss;
        this.dolencia = dolencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNss() {
        return nss;
    }

    public void setNss(int nss) {
        this.nss = nss;
    }

    public String getDolencia() {
        return dolencia;
    }

    public void setDolencia(String dolencia) {
        this.dolencia = dolencia;
    }
}
