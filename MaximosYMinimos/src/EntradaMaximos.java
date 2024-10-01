public class EntradaMaximos {

    public static void main(String[] args) {
        System.out.println("Se mostraran los MAX y MIN de cada tipo:");
        MaxYMin();
        System.out.println("Terminando la ejecucion.....");
    }

    public static void MaxYMin(){
        System.out.println("El tipo byte tiene un maximo de "+Byte.MAX_VALUE+" y un minimo de "+Byte.MIN_VALUE);
        System.out.println("El tipo short tiene un maximo de "+Short.MAX_VALUE+" y un minimo de "+Short.MIN_VALUE);
        System.out.println("El tipo int tiene un maximo de "+Integer.MAX_VALUE+" y un minimo de "+Integer.MIN_VALUE);
        System.out.println("El tipo long tiene un maximo de "+Long.MAX_VALUE+" y un minimo de "+Long.MIN_VALUE);
        System.out.println("El tipo double tiene un maximo de "+Double.MAX_VALUE+" y un minimo de "+Double.MIN_VALUE);
        System.out.printf("El tipo float tiene un maximo de %2f y un minimo de %2f\n", Float.MAX_VALUE, Float.MIN_VALUE);
       // El "2" del "%2f" es el numero de decimales que quiero que me de, si no pongo numero me salen todos los decimales que tenga
        // Se puede hacer de las dos maneras: println (el "normal") o printf (el del "%")
    }
}
