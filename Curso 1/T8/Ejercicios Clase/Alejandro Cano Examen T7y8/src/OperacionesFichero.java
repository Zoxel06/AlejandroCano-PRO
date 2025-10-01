import java.io.*;

public class OperacionesFichero {

    public void descifrarCodigo(String path) {

        File file = new File(path);
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            String linea = null;

            while ((linea = bufferedReader.readLine()) != null) {


                String[] palabras = linea.split(" ");

                for (int i = 0; i < linea.length(); i++) {
                    String codigo = palabras[i];
                    char codigoDescifrado = (char) Integer.parseInt(codigo);
                    System.out.print(codigoDescifrado);
                }
            }

        } catch (IOException e) {
            System.out.println("Error en la lectura");
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }

    }

}
