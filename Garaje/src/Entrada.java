public class Entrada {

    public static void main(String[] args) {
        Garaje garaje = new Garaje();
        System.out.println("Bienvenido a la app de gestión de coches");
        garaje.anadirCoches("Mercedes", "C22O", 200, "1234A");
        garaje.anadirCoches("Ford", "Fiesta", 100, "1234B");
        garaje.anadirCoches("Audi", "A3", 150, "1234C");
        garaje.anadirCoches("Porsche", "911", 300, "1234C");
        garaje.anadirCoches("Formula", "1", 500, "1234E");
        garaje.mostrarCoches();
    }

}
