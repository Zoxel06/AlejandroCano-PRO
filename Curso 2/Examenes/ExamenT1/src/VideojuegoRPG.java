import model.ClasificacionEdad;
import model.Descargable;
import model.Videojuego;

public class VideojuegoRPG extends Videojuego implements Descargable {

    private boolean mundoAbierto;
    private int horasHistoriaPrincipal;

    public VideojuegoRPG(){
    }

    public VideojuegoRPG(String titulo, String desarrollador, int anioLanzamiento, double precio, ClasificacionEdad clasificacionEdad, int horasHistoriaPrincipal, boolean mundoAbierto) {
        super(titulo, desarrollador, anioLanzamiento, precio, clasificacionEdad);
        this.horasHistoriaPrincipal = horasHistoriaPrincipal;
        this.mundoAbierto = mundoAbierto;
    }

    @Override
    public String toString() {
        System.out.println(super.toString());
        return
                "mundoAbierto=" + mundoAbierto +
                ", horasHistoriaPrincipal=" + horasHistoriaPrincipal;
    }

    @Override
    public double calcularPrecioFinal() {
        if (mundoAbierto) {
            setPrecio(((15*getPrecio()) / 100) + getPrecio());
        }
        double precioProvisional = 0.0;
        for (int i = 0; i < horasHistoriaPrincipal/10; i++) {
            precioProvisional = precioProvisional + (((2*getPrecio()) / 100) + getPrecio());
        }
        setPrecio(precioProvisional);
        return getPrecio();
    }

    public boolean isMundoAbierto() {
        return mundoAbierto;
    }

    public void setMundoAbierto(boolean mundoAbierto) {
        this.mundoAbierto = mundoAbierto;
    }

    public int getHorasHistoriaPrincipal() {
        return horasHistoriaPrincipal;
    }

    public void setHorasHistoriaPrincipal(int horasHistoriaPrincipal) {
        this.horasHistoriaPrincipal = horasHistoriaPrincipal;
    }
}
