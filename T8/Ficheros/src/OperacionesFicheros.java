import java.io.*;
import java.util.Scanner;

public class OperacionesFicheros {


    public void leerInformacion() {
        File file = new File("C:\\Users\\Usuario DAM1\\Documents\\GitHub Alejandro Cano\\AlejandroCano-PRO");
        System.out.println(file.exists());
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());

        file.list(); // nombre de los ficheros que estan dentro

        for (String nombre : file.list()) {
            System.out.println(nombre);
        }

        file.listFiles(); // los ficheros que estan dentro

        for (File fichero : file.listFiles()) {
            System.out.println(fichero);
        }

    }

    public void crearDirectorio(String path) {
        // src/recursos/lectura
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();

            // mkdir cuando solo cree una carpeta
            // mkdirs cuando cree una o mas carpetas dentro de otra carpeta creada ahora
        }
    }

    public void crearFichero(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Fallo en la escritura del fichero");
            }

        }
    }

    public void escribirFichero(String path) {
        File file = new File(path);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file,true); // el true hace que si ya habia escrito algo anteriormente no desaparezca por lo nuevo
            fileWriter.write("Esto es un mensaje");
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

    public void escribirDatos(String path, String nombre, String apellido, int edad,String mensaje){
        File file = new File(path);
        FileWriter fileWriter = null;

        try{
            fileWriter = new FileWriter(file,true);
            fileWriter.write("Nombre: " + nombre + "\n");
            fileWriter.write("Apellido: " + apellido + "\n");
            fileWriter.write("Edad: " + edad + "\n");
            fileWriter.write("Mensaje: " + mensaje + "\n");
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

    public void lecturaUnitaria(String path) {
        File file = new File(path);
        if (file.exists()) {
            // hago lectura
            FileReader fileReader = null;

            try {
                fileReader = new FileReader(file);
                int lectura = fileReader.read();
                System.out.println("lectura = " + lectura + " asociado a la letra " + (char) lectura);// para que lea cada letra y su correspondiente caracter en ascii
                lectura = fileReader.read();
                System.out.println("lectura = " + lectura + " asociado a la letra " + (char) lectura);
            } catch (FileNotFoundException e) {
                System.out.println("El fichero no existe");
            } catch (IOException e) {
                System.out.println("Error en la lectura");
            }

        } else {
            System.out.println("No se puede leer, el fichero no existe");
        }
    }

    public void lecturaCompleta(String path) {
        File file = new File(path);
        if (file.exists()) {
            // hago lectura
            FileReader fileReader = null;

            try {
                fileReader = new FileReader(file);
                int lectura = 0;
                while ((lectura = fileReader.read()) != -1) {
                    System.out.print((char) lectura); // para que lea lo de dentro
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

    public void lecturaCodigoUnitaria(String path) {
        File file = new File(path);
        if (file.exists()) {
            // hago lectura
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;

            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);

                String linea = bufferedReader.readLine();
                System.out.println("La linea leida es: " + linea);
                linea = bufferedReader.readLine();
                System.out.println("La linea leida es: " + linea);

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

    public void lecturaCodigoCompleta(String path) {
        File file = new File(path);
        if (file.exists()) {
            // hago lectura
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;

            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);

                String linea = null;
                while ((linea = bufferedReader.readLine()) != null) {

                    //StringBuffer lineas = new StringBuffer();


                    // System.out.println(linea);
                    String[] letras = linea.split(" ");
                    for (String letra : letras) {
                        System.out.print("letra = " + letra);
                        System.out.println();
                    }

                    for (String letra: letras) {
                        int letraNumero = Integer.parseInt(letra);
                        System.out.println((char)letraNumero + "\t");
                    }
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
