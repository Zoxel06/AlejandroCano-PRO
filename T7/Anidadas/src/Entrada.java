import model.ClasePadre;

public class Entrada {
    public static void main(String[] args) {

        ClasePadre clasePadre = new ClasePadre("Alejandro","Cano",19,"correo");
        clasePadre.mostrarDatos();

        System.out.println("_______________________");

        ClasePadre.ClaseHija claseHija = clasePadre.new ClaseHija("a","a",1,"a");
        clasePadre.mostrarDatos();

    }
}
