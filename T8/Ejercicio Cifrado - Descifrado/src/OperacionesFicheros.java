import java.io.*;

public class OperacionesFicheros {

    // CIFRAR: File --> FileWriter --> BufferedWriter o PrintWriter
    // DESCIFRAR: File --> FileReader --> BufferedReader


    public void cifrarMensaje(String path, String mensaje, int fase) {
        File file = new File(path);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file, true);

            for (int i = 0; i < mensaje.length(); i++) {
                char letra = mensaje.charAt(i); //--> para guardar cada caracter del mensaje por separado

                int codigo = (int) letra; //--> para sacar el numero ascii de los caracteres

                int codigoCifrado = codigo * fase;

                fileWriter.write(codigoCifrado); //--> si lo quiero guardar con caracteres raros al multiplicarlo (cuando el caracter supere el 255)
                //fileWriter.write(String.valueOf(codigoCifrado)); --> se guarda como un String, por lo que quedan numeros
            }

        } catch (IOException e) {
            System.out.println("Error en la escritura");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }

    }

    public void descifrarCodigo(String path, String mensaje, int fase) {
        File file = new File(path);
        FileReader fileReader = null;
        // BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(file); //--> caracter a caracter
            // bufferedReader = new BufferedReader(fileReader); //--> linea a linea
            int codigoLectura = -1;
            // String lectura = null;
            while ((codigoLectura = fileReader.read()) != -1) {

                int codigo = codigoLectura / fase;

                char codigoDescifrado = (char) codigo;

                System.out.println(codigoDescifrado);

            }

             /*

             while ((lectura = bufferedReader.readLine()) != null) {
                String[] codigos = lectura.split(" "); //--> para separar cada conjunto de numeros segun los espacios
                for(String codigo: codigos){
                int codigoDescifrado = Integer.valueOf(codigo) / fase;
                    System.out.println((char) codigoDescifrado);
                }
               }

              */


        } catch (FileNotFoundException e) {
            System.out.println("Error en la lectura");
        } catch (IOException e) {
            System.out.println("Error en la lectura");
        } finally {
            try {
                fileReader.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }

    }

    public void escrituraFicheroCompleto(String path) {

        File file = new File(path);
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            fileWriter = new FileWriter(file,true);
            printWriter = new PrintWriter(fileWriter);

            printWriter.println("Esta primera linea es la escritura completa");
            printWriter.println("Esta segunda linea se ha incorporado de forma automatica");
            printWriter.println(69);
        } catch (IOException e) {
            System.out.println("Error en la escritura");
        }finally {
            try {
                fileWriter.close();
                printWriter.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }

    }


}
