package model;

public class Multimedia {

    private String id, titulo, formato;
    private int tamanio;
    private Persona autor;

    public Multimedia(){}

    public Multimedia(String id, String titulo, String formato, int tamanio, Persona autor) {
        this.id = id;
        this.titulo = titulo;
        this.formato = formato;
        this.tamanio = tamanio;
        this.autor = autor;
    }

    public void mostrarDatos(){
        System.out.println("Id: " + id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Formato: " + formato);
        System.out.println("Tamaño: " + tamanio);
        System.out.println("Autor: " + autor.getNombre()); //como el autor es de tipo persona pillo el nombre recogido en esa clase
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamaño(int tamaño) {
        this.tamanio = tamanio;
    }

    public Persona getAutor() {
        return autor;
    }

    public void setAutor(Persona autor) {
        this.autor = autor;
    }
}
