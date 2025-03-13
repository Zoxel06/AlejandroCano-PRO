package model;

public class General extends Doctor{

    private String tipo;

    public General() {
    }

    public General(String nombre, String apellido, int numeroColegiado, String tipo) {
        super(nombre, apellido, numeroColegiado);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void recetarPastillas(){
        System.out.println("Pastillas recetadas");
    }

}
