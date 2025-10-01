public class PrácticaClaseArray {

    public static void main(String[] args) {


        int[] numeros = new int[10];
        // 0 0 0 0 0 0 0 0 0 0 (10 ceros)


        /*
        System.out.println("La longitud es " + numeros.length);
        // cuantas posiciones tiene

        numeros[3] = 7;
        // 0 0 0 7
        System.out.println("El numero en la ultima posicion es " + numeros[numeros.length - 1]);

        String[] palabras = {"Hola", "Que", "Tal", "Estás", "Esto", "Es", "Una", "Prueba"};
        System.out.println("La palabra en la última posición es " + palabras[palabras.length - 1]);

        for (int i = 0; i < palabras.length; i++) {
            System.out.println("La palabra es " + palabras[i]);
        }

        */
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = (int) (Math.random() * 101);
        }



        // el for es para recorrer una coleccion donde yo indico el principio y el final

        /*
        for (int i = 0; i < numeros.length; i++) {
            // los numeros que estan en posicion par
            if (i % 2 == 0) {
                System.out.println(numeros[i]);
            }

            // los numeros que son pares
            if (numeros[i] % 2 == 0){
                System.out.println(numeros[i]);
            }
        }
        */

        for (int item : numeros){
            if (item % 2 == 0){
            System.out.println(item);
            }
        }
    }
}
