import java.io.*;

public class OperacionesUsuario {

    public void escribirUsuarioObjeto(String path, Usuario usuario) {

        File file = new File(path);
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(usuario);

        } catch (FileNotFoundException e) {
            System.out.println("Error en la escritura");
        } catch (IOException e) {
            System.out.println("Error en la escritura");
        } finally {
            try {
                fileOutputStream.close();
                objectOutputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }
    }

}
