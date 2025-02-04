package model;

public class Libro extends Multimedia{

    private String isbn;
    private int numeroPaginas;

    public Libro(){}

    public Libro(String id, String titulo, String formato, int tamaño, Persona autor, String isbn, int numeroPaginas) {
        super(id, titulo, formato, tamaño, autor);
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("ISBN: " + isbn);
        System.out.println("Numero de Paginas: " + numeroPaginas);
    }
}
