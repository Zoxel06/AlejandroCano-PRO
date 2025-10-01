public class Entrada {

    static int edadClase = 20;

    //mod_acc mod_adional mod_ret nombre (argumentos) { cuerpo}
    public static void main(String[] args) {
        System.out.println("Proyecto de variables");
        miMetodo();
        otroMetodo();

        // Tipo nombre = valor (el valor es opcional)
        String nombre = "Alejandro";
        final String CIF = "B12345"; // con el modificador "final" hago que la variable sea constante
        // las variables finales van siempre escritas en mayúscula
        char letra = 'A';
        Character letraCompleja = 'a';

        // el byte puede guardar los numeros 1 y 0 (creo)
        short numeroPequeno = 23; //numero pequeno (-255, 255)
        Short pequenoCompleto = 23;

        int edad = 18; // numero sin decimal (-65.435, 65.435)
        System.out.println("Mi edad es " + edad);
        System.out.println("Cambiando edad...");
        edad = 19; // para cambiar el valor no puedo usar "int" otra vez porque no se pueden asginar 2 datos diferentes a la misma variable
        System.out.println("Tengo " + edad + " años");
        Integer edadCompleja = 18;
        long telefono = 123456789;
        Long telefonoCompleto;
        float altura = 3.14f;
        Float alturaCompleja;

        double decimalesDetalle = 3.141516238;
        Double decimalesComplejos;

        boolean carnet = true; //los boolean son o true o false
        Boolean carnetComplejo;

    }

    public static void miMetodo() {
        String nombre = "Alejandro";
        int edad = 40;
        edadClase = 80;
        //this.edadClase (si quiero usar la edadClase de arriba, la static)
    }

    public static void otroMetodo() {
        int edadClase = 90;
        //this.edadClase = 90;

    }
}