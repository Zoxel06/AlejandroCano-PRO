package model;

import java.util.Scanner;

public class Tarea {

    Scanner scanner = new Scanner(System.in);

    // variables
    private String titulo, descripcion;
    private boolean prioritario, completada;
    private Persona[] encargados;
    // constructores


    public Tarea() {
    }

    public Tarea(String titulo, String descripcion, boolean prioritario, int numeroPersonas) {
        // completada =false por defecto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioritario = prioritario;
        encargados = new Persona[numeroPersonas];
    }

    public Tarea(String descripcion, String titulo, int numeroPersonas) {
        // completada = false;
        // preioritario = false;
        this.descripcion = descripcion;
        this.titulo = titulo;
        encargados = new Persona[numeroPersonas];
    }
    // metodos -> getter/setter

    // cada tarea puede asignar una persona
    // solo se podrá asignar una persona si hay hueco disponible
    // cada vez que se asigne una persona, se deberá colocar en el primer hueco disponible
    // si no hay hueco saltar un aviso

    // en una tarea se pueden quitar responsables. Solo podre quitar un responsable si el DNI que me indicas esta dentro de la lista de responsables
    // Mostrar aviso tanto para proceso OK como proceso no OK

    // Mostrar los datos de todos los usuarios que son responsables de dicha tarea
    // en caso de no tener ninguno avisar
    // en caso de tener huecos disponibles, avisar de cuantos

    // creamos un encargo
    // Los encargos tienen una propiedad llamada descripcion, una propiedad llamada id y una propiedad llamada completa

    public void asignarPersona(Persona persona) {
        for (int i = 0; i < encargados.length; i++) {
            if (encargados[i] == null && comprobarDNI(persona.getDni())) {
                System.out.println("Persona agregada correctamente");
                encargados[i] = persona;
                return;
            }
        }
        System.out.println("No se ha podido agregar a la persona");
    }

    public boolean comprobarDNI(String dni) {
        for (int i = 0; i < encargados.length; i++) {
            if (encargados[i] != null) {
                if (dni.equalsIgnoreCase(encargados[i].getDni())) {
                    System.out.println("No puedes agregar a dos personas con el mismo DNI");
                    return false;
                }
            }

        }
        return true;
    }

    public void eliminarPersona(String dni) {
        for (int i = 0; i < encargados.length; i++) {
            if (encargados[i] != null && dni.equalsIgnoreCase(encargados[i].getDni())) {
                encargados[i] = null;
                System.out.println("Encargado eliminado correctamente");
                return;
            }
        }
        System.out.println("DNI no registrado, no se puede eliminar a nadie");
    }

    public void mostrarDatos() {
        int huecos = 0;
        for (int i = 0; i < encargados.length; i++) {
            if (encargados[i] != null){
                System.out.println("Encargado " + (i+1) + "- Nombre: " + encargados[i].getNombre() + ", Apellido: " + encargados[i].getApellido() + ", Edad: " + encargados[i].getEdad() + ", DNI: " + encargados[i].getDni());
            } else {
                huecos++;
            }

        }

        if (huecos == encargados.length) {
            System.out.println("Están todos los huecos disponibles");
        }
        else if (huecos > 0) {
            System.out.printf("Hay %d huecos disponibles",huecos);
        } else {
            System.out.println("No hay huecos disponibles");
        }

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isPrioritario() {
        return prioritario;
    }

    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", prioritario=" + prioritario +
                ", completada=" + completada +
                '}';
    }

}
