import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;

public class Operaciones {

    public void leerInformacion(){
        File file = new File("C:\\Users\\Usuario DAM1\\Desktop");

        System.out.println("Ficheros: \n");
        for (File fichero : file.listFiles()){
            if (fichero.isFile()){
                System.out.println(fichero.getName());
            }
        }

        /* Recorro todos los ficheros contenidos en la ruta e imprimo su nombre */

        System.out.println();

        System.out.println("                Carpetas: \n");
        for (File carpeta : file.listFiles()) {
            if (carpeta.isDirectory()){
                System.out.println("                " + carpeta.getName());
            }
        }

        /* Recorro todas las carpetas contenidas en la ruta e imprimo su nombre */


        /* No estoy seguro si esto es lo que pedias o me faltan cosas pero para entregar algo aunque sea te mando esto */

    }

}
