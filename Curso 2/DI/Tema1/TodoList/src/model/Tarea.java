package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Tarea {


    // variables
    private String titulo, descripcion;
    private boolean prioritario, completada;
    private Persona[] encargados;
    private ArrayList<Encargos> listaTareas;

    /*
    Listar todos los encargos de una tarea
    Buscar un encargo por id y mostrar sus datos
    Completar un encargo -> pasar su variable completada a true
    Mostrar un encargo que estan completados
    Completar una tarea -> Una tarea quedará completa si todos sus encargos están completos
     */

    /*
    Crear los metodos de: asignar a un encargo un responsable
    Cambiar responsable de tarea
    Mostrar tareas por responsable -> DNI
     */


    // constructores


    public Tarea() {
    }

    public Tarea(String titulo, String descripcion, boolean prioritario, int numeroPersonas) {
        // completada =false por defecto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioritario = prioritario;
        encargados = new Persona[numeroPersonas];
        listaTareas = new ArrayList<>();
    }

    public Tarea(String descripcion, String titulo, int numeroPersonas) {
        // completada = false;
        // prioritario = false;
        this.descripcion = descripcion;
        this.titulo = titulo;
        encargados = new Persona[numeroPersonas];
        listaTareas = new ArrayList<>();
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
            if (encargados[i] != null) {
                System.out.println("Encargado " + (i + 1) + "- Nombre: " + encargados[i].getNombre() + ", Apellido: " + encargados[i].getApellido() + ", Edad: " + encargados[i].getEdad() + ", DNI: " + encargados[i].getDni());
            } else {
                huecos++;
            }

        }

        if (huecos == encargados.length) {
            System.out.println("Están todos los huecos disponibles");
        } else if (huecos > 0) {
            System.out.printf("Hay %d huecos disponibles", huecos);
        } else {
            System.out.println("No hay huecos disponibles");
        }
    }

    private Encargos estaEncargo(int id) {
        for (Encargos item : listaTareas) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void agregarEncargo(Encargos encargo) {
        if (estaEncargo(encargo.getId()) != null) {
            System.out.println("No puedes añadir 2 rencargos con el mismo id");
        } else {
            listaTareas.add(encargo);
            System.out.println("Encargo agregado correctamente");
        }
    }

    public void eliminarEncargo(int id) {
        if (estaEncargo(id) != null) {
            listaTareas.remove(estaEncargo(id));
            System.out.println("Encargo borrado correctamente");
        } else {
            System.out.println("No se encuentra el id especificado en la lista de encargos");
        }
    }

    public void listarEncargos() {
        for (Encargos item : listaTareas) {
            System.out.println("Id: " + item.getId());
            System.out.println("Descripcion: " + item.getDescripcion());
            System.out.println("Completada: " + item.isCompletada());
        }
    }

    public void buscarEncargo(int id) {
        for (Encargos item : listaTareas) {
            if (item.getId() == id) {
                System.out.println("Id: " + item.getId());
                System.out.println("Descripcion: " + item.getDescripcion());
                System.out.println("Completada: " + item.isCompletada());
                return;
            }
        }
        System.out.println("Id no encontrado en la lista");
    }

    public void completarEncargo(int id) {
        for (Encargos item : listaTareas) {
            if (item.getId() == id) {
                item.setCompletada(true);
                System.out.println("Encargo completado correctamente");
                return;
            }
        }
        System.out.println("Id no encontrado en la lista o ya está completado");
    }

    public void mostrarEncargosCompletados() {
        for (Encargos item : listaTareas) {
            if (item.isCompletada()) {
                System.out.println("Id: " + item.getId());
                System.out.println("Descripcion: " + item.getDescripcion());
                System.out.println("Completada: " + item.isCompletada());
            }
        }
    }


    public void completarTarea() {
        for (Encargos item : listaTareas) {
            if (!item.isCompletada()) {
                System.out.println("Hay encargos sin completar");
                return;
            }
        }
        completada = true;
        System.out.println("Tarea completada");
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

    public ArrayList<Encargos> getListaTareas() {
        return listaTareas;
    }

    public void setListaTareas(ArrayList<Encargos> listaTareas) {
        this.listaTareas = listaTareas;
    }

    public Persona[] getEncargados() {
        return encargados;
    }

    public void setEncargados(Persona[] encargados) {
        this.encargados = encargados;
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
