import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Controladora1 {

    static Scanner scanner = new Scanner(System.in);

    private ArrayList<Object[]> listaProductos;

    public Controladora1() {
        listaProductos = new ArrayList<>();
    }

    public void agregarProducto(String nombre, int precio) {
        Object[] producto = new Object[]{nombre, precio};
        System.out.println("Producto:");
        nombre = scanner.next();
        System.out.println("Precio");
        precio = scanner.nextInt();
        listaProductos.add(producto);
    }

    public void listarProductos() {
        for (Object[] item : listaProductos) {
            System.out.println(item[0] + " - " + item[1] + "$");
        }
    }

    public void listarPrecioMinimo(int precio) {
        for (Object[] item : listaProductos) {
            if ((int) item[1] >= precio) {
                System.out.println(item[0] + " - " + item[1]);
            }
        }
    }

    public void listarOrdenado() {
        listaProductos.sort(new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                // condicion de ordenacion
                if ((int) o1[1] > (int) o2[1]) {
                    return 1;
                } else if ((int) o1[1] < (int) o2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        listarProductos();
    }

}
