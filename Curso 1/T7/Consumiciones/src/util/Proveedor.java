package util;

public enum Proveedor {

    // tipos predefiniidos
    MAHOU("Cerveza mahou",5,"Juan Gomez"),
    COCACOLA("Bebida gaseosa",10,"Julia Martinez"),
    COMIDASSL(),
    BEBIDASSL("Bebidas generales",0,"Pepe Mel");

    // variables
    private String nombre;
    private int descuento;
    private String contacto;

    // constructores
    Proveedor(){
        this.nombre = "sin nombre";
        this.descuento = 0;
        this.contacto = "sin contacto";
    }

    Proveedor(String nombre, int descuento, String contacto){
        this.nombre = nombre;
        this.descuento = descuento;
        this.contacto = contacto;
    }

    //metodos


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
}
