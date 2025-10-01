public class Entrada {

    public static void main(String[] args) {
        OperacionesFichero operacionesFichero = new OperacionesFichero();

        Inventario inventario = new Inventario<Inventario.Producto>();

        Inventario.Producto producto1 = new Inventario.Producto("Leche",5.00,20);
        Inventario.Producto producto2 = new Inventario.Producto("Huevos",7.00,50);
        Inventario.Producto producto3 = new Inventario.Producto("Pan",1.50,35);

        inventario.agregarElemento(producto1);
        inventario.agregarElemento(producto2);
        inventario.agregarElemento(producto3);

        inventario.mostrarInventario();

        operacionesFichero.descifrarCodigo("src/ejercicio1.txt");
        


    }

}
