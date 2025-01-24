import java.util.ArrayList;
import java.util.Scanner;

public class Persona {
    static Scanner scanner = new Scanner(System.in);

    private String nombre, apellido, dni;
    private int edad;
    private double peso, altura;


    public Persona(String nombre, String apellido, String dni, int edad, double peso, double altura) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
    }

    public Persona(String nombre, String apellido, String dni, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
    }

    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = "11111111X";
    }

    public Persona() {
        this.nombre = "sin definir";
        this.apellido = "sin definir";
        this.dni = "11111111X";
    }





    public void mostrarDatos() {
        System.out.printf("Nombre: %s\nApellido: %s\nDNI: %s\nEdad: %d\nPeso: %.2fkg\nAltura: %.2fm\n"
                ,nombre,apellido,dni,edad,peso,altura);
        System.out.println();
    }

    public void incrementarEdad(){
        System.out.println("Dime cuantos años quieres aumentar");
        int aumento = scanner.nextInt();
        setEdad(edad+aumento);
    }

    public double calcularIMC(){
        double imc = peso/Math.pow(altura,altura);
        return imc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

}
