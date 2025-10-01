package model;

public class Digestivo extends Doctor{
    private int añosExperiencia;

    public Digestivo() {
    }

    public Digestivo(String nombre, String apellido, int numeroColegiado, int añosExperiencia) {
        super(nombre, apellido, numeroColegiado);
        this.añosExperiencia = añosExperiencia;
    }

    public int getAñosExperiencia() {
        return añosExperiencia;
    }

    public void setAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }

    public void realizarIntervencion(){
        System.out.println("Intervencion realizada");
    }

}
