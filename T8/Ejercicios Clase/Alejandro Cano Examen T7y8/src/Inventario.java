import java.lang.reflect.Array;
import java.util.ArrayList;

public class Inventario<T extends Inventario.Producto> {

    private Producto producto;

    private ArrayList<T> listaElementos;

    public Inventario() {
        this.listaElementos = new ArrayList<>();
    }

    public Inventario(Producto producto) {
        this.listaElementos = new ArrayList<>();
        this.producto = producto;
    }


    public void agregarElemento(T elemento){
        listaElementos.add(elemento);
    }

    public void mostrarInventario(){
        if (listaElementos != null){
            System.out.println("Productos:");

            for (T item : listaElementos) {
                System.out.println(item.getNombre());
                System.out.println(item.getPrecio());
                System.out.println(item.getStock());
            }
        }
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public static class Producto {

        private String nombre;
        private double precio;
        private int stock;

        public Producto(){

        }

        public Producto(String nombre, double precio, int stock) {
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
        }

        @Override
        public String toString() {
            return "Producto{" +
                    "nombre='" + nombre + '\'' +
                    ", precio=" + precio +
                    ", stock=" + stock +
                    '}';
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }
    }

}
