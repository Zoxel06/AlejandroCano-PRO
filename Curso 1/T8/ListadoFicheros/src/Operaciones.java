import java.io.File;

public class Operaciones {

    private int contadorNivel;

    public void leerInformacion() {
        File file = new File("C:\\Users\\Usuario DAM1\\Desktop");

        for (File fichero : file.listFiles()) {
            recorrerDirectorio(fichero);

        }
    }

    public void recorrerDirectorio(File fichero) {
        for (int i = 0; i < contadorNivel; i++) {
            System.out.print("\t");
        }
        System.out.println(fichero.getName());
        if (fichero.isDirectory()){
            contadorNivel++;
            for (File f : fichero.listFiles()){
                recorrerDirectorio(f);
            }
        }
    }

}
