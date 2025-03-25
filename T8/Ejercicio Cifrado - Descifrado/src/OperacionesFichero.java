import java.io.*;

public class OperacionesFichero {

    public void escribirMensaje(String path, String mensaje) {
        File file = new File(path);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file,true);

            fileWriter.write(mensaje);



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

    public void cifrarMensaje(String path){
        File file = new File(path);
        if (file.exists()) {
            // hago lectura
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;
            StringBuffer lineas = null;

            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                lineas = new StringBuffer();

                String linea = null;
                while ((linea = bufferedReader.readLine()) != null) {


                    String[] numeros = linea.split(" ");


                }

            } catch (FileNotFoundException e) {
                System.out.println("El fichero no existe");
            } catch (IOException e) {
                System.out.println("Error en la lectura");
            } finally {
                System.out.println("Terminando lectura");
                try {
                    fileReader.close();
                } catch (IOException | NullPointerException e) {
                    System.out.println("Error en el cierre del fichero");
                }
            }

        } else {
            System.out.println("No se puede leer, el fichero no existe");
        }
    }

}
