import controller.Restaurante;
import model.Bebida;
import model.Cliente;
import model.Consumicion;
import util.Proveedor;

public class Entrada {

    public static void main(String[] args) {
        /*
        Bebida bebida = new Bebida("Cerveza",2,300);
        Racion racion = new Racion("Patatas",4,1);
        Comida comida = new Comida("Bocadicallo",3,1000);

        bebida.mostrarDatos();
        racion.mostrarDatos();
        comida.mostrarDatos();
        */

        Restaurante restaurante = new Restaurante(Proveedor.COMIDASSL);

        restaurante.informacionProveedor();

        restaurante.agregarCliente(new Cliente("Alejandro1"));
        restaurante.agregarCliente(new Cliente("Alejandro2"));
        restaurante.agregarCliente(new Cliente("Alejandro2"));
        restaurante.agregarPedido(new Bebida("Cocacola",3,300), "Alejandro1");
        restaurante.agregarPedido(new Bebida("Cocacola",3,300), "Alejandro1");
        restaurante.mostrarClientes();

        //restaurante.mostrarClientes();

        // restaurante.mostrarCajaRestaurante();

    }
}