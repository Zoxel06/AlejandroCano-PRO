import utils.LetraException;
import utils.LongitudException;

public class Formulario {

    private String nombre;
    private String apellido;
    private String dni;

    public void mostrarDatos() {
        if (nombre == null || apellido == null || dni == null) {
            throw new NullPointerException("Alguno de los datos no esta relleno");
        } else {
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellido: " + apellido);
            System.out.println("DNI: " + dni);
        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws LongitudException {
        if (nombre == null || nombre.isEmpty()) {
            throw new LongitudException("El nombre introducido no es valido");
        }
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) throws LongitudException {
        if (apellido == null || apellido.isEmpty()) {
            throw new LongitudException("El apellido introducido no es valido");
        }
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws LetraException, LongitudException {
        char ultimaLetra = dni.charAt(dni.length() - 1);
        // si ultimaLetra no es un letra
        if (Character.isDigit(ultimaLetra)) {
            throw new LetraException("El último digito no es una letra");
        }
        if (dni.length() != 9) {
            throw new LongitudException("El tamaño del dni no es correcto");
        }
        this.dni = dni;

    }
}