import java.io.File;

public class OperacionesFicheros {

    public void leerInformacion() {
        File file = new File("C:\\Users\\Usuario DAM1\\Documents\\GitHub Alejandro Cano\\AlejandroCano-PRO");
        System.out.println(file.exists());
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        file.list(); // nombre de los ficheros que estan dentro

        for (String nombre : file.list()){
            System.out.println(nombre);
        }

        file.listFiles(); // los ficheros que estan dentro

        for (File fichero : file.listFiles()){
            System.out.println(fichero);
        }

    }


}
