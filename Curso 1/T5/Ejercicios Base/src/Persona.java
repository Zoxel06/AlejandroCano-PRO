import java.util.Scanner;

public class Persona {
    static Scanner scanner = new Scanner(System.in);

    private String nombre, apellido, dni, genero;
    private int edad;
    private double peso, altura;


    public Persona(String nombre, String apellido, String dni, int edad, double peso, double altura, String genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.genero = genero;
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
        this.genero = "sin definir";
    }

    public Persona() {
        this.nombre = "sin definir";
        this.apellido = "sin definir";
        this.dni = "11111111X";
        this.genero = "sin definir";
    }


    public void mostrarDatos() {
        System.out.printf("Nombre: %s\nApellido: %s\nDNI: %s\nEdad: %d\nPeso: %.2fkg\nAltura: %.2fm\nGenero: %s\n"
                , nombre, apellido, dni, edad, peso, altura, genero);
        System.out.println();
    }

    public void incrementarEdad() {
        System.out.println("Dime cuantos años quieres aumentar");
        int aumento = scanner.nextInt();
        setEdad(edad + aumento);
    }

    public double calcularIMC() {
        double imc = peso / Math.pow(altura, altura);
        return imc;
    }

    public void determinarPeso() {
        if (calcularIMC() < 18.5) {
            System.out.println("Peso inferior al normal");
        } else if (calcularIMC() >= 18.5 && calcularIMC() <= 24.9) {
            System.out.println("Peso normal");
        } else if (calcularIMC() >= 25.0 && calcularIMC() <= 30) {
            System.out.println("Peso superior al normal");
        } else if (calcularIMC() > 30.0) {
            System.out.println("Obesidad");
        }
    }

    public void determinarPesoGenero() {
        if (genero.equalsIgnoreCase("hombre")) {
            if (calcularIMC() < 20) {
                System.out.println("Bajo peso");
            } else if (calcularIMC() >= 20 && calcularIMC() < 27) {
                System.out.println("Normopeso");
            } else if (calcularIMC() >= 27 && calcularIMC() < 30) {
                System.out.println("Sobrepeso");
            } else if (calcularIMC() >= 30 && calcularIMC() <= 40) {
                System.out.println("Obesidad");
            } else if (calcularIMC() > 40) {
                System.out.println("Obesidad mórbida");
            }
        } else if (genero.equalsIgnoreCase("mujer")) {
            if (calcularIMC() < 20) {
                System.out.println("Bajo peso");
            } else if (calcularIMC() >= 20 && calcularIMC() < 25) {
                System.out.println("Normopeso");
            } else if (calcularIMC() >= 25 && calcularIMC() < 30) {
                System.out.println("Sobrepeso");
            } else if (calcularIMC() >= 30 && calcularIMC() <= 40) {
                System.out.println("Obesidad");
            } else if (calcularIMC() > 40) {
                System.out.println("Obesidad mórbida");
            }
        }
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
