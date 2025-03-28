import java.io.*;

public class OperacionesUsuario {

    public void escribirUsuario(String path, Usuario usuario) {
        File file = new File(path);
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(new FileWriter(file, true));

            printWriter.println(usuario.toString());

        } catch (IOException e) {
            System.out.println("Error en la escritura");
        } finally {
            try {
                printWriter.close();
            } catch (NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }
    }

    public void lecturaUsuario(String path) {
        File file = new File(path);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(", "); //--> hay que poner el formato con el que lo haya separado en el txt
                Usuario usuario = new Usuario(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]));
                usuario.mostrarDatos();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error en la lectura");
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

    public void leerUsuarioObjeto(String path) {
        File file = new File(path);
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);

            Usuario usuario = (Usuario) (objectInputStream.readObject());

            usuario.mostrarDatos();

        } catch (FileNotFoundException e) {
            System.out.println("Error en la lectura");
        } catch (IOException e) {
            System.out.println("Error en la lectura");
        } catch (ClassNotFoundException e) {
            System.out.println("Error en encontrar la clase");
        } catch (ClassCastException e) {
            System.out.println("Error en el casteo de las clases");
        } finally {
            try {
                fileInputStream.close();
                objectInputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }

    }

}
