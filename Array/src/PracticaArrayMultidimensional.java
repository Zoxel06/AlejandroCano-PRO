public class PracticaArrayMultidimensional {

    public static void main(String[] args){
    //teoria();
    ejemplo1();


    }
    public static void teoria(){

        int[][] matriz = new int[4][4]; // [filas][columnas]

        int[][] numeros = {
                {4,8,9},
                {3,2,7},
                {9,20,40}
        };

        int[][] numeros2 = {
                {4,8,9,2,5},
                {3,2,7},
                {9,20,40}
        };

        for (int i = 0; i < numeros.length; i++){
            for (int j = 0; j < numeros[i].length; j++){
                System.out.print(numeros[i][j]);
            }
            System.out.println();
        }
    }

    public static void ejemplo1() {
        int[][] numeros = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16,17}
        };

        //cuantas filas tengo
        int numeroFilas = numeros.length; //4
        //las columnas de la fila
        int columnasFila1 = numeros[0].length;
        int columnasFila2 = numeros[1].length;
        int columnasFila3 = numeros[2].length;
        int columnasFila4 = numeros[3].length;
        //sacar el ultimo numero
        //numeros[3][4]
        int ultimoNumero = numeros[numeros.length-1][numeros[numeros.length-1].length-1];

        int primerValor = numeros[0][0];
        //modificar valor de una posicion
        numeros[0][0] = 777;
        //acceder a todos los elementos
        //recorrer filas
        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros[i].length; j++) {
                System.out.print(numeros[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        for ( int[] item : numeros ) {
            for ( int element : item ) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
    }


}
