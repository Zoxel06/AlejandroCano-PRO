package model;

public class Coche {

    private String matricula, modelo;
    private int cv, velocidad;
    private double km;

    public Coche() {
        this.matricula = "0000AAA";
        this.modelo = "sin especificar";
        this.cv = 0;
        this.velocidad = 0;
        this.km = 0.0;
    }

    public Coche(String modelo, String matricula, int cv) {
        this.modelo = modelo;
        this.matricula = matricula;
        this.cv = cv;
    }


    public void acelerar(int velocidad) {

        this.velocidad += velocidad;

        if (this.velocidad > 180) {
            this.velocidad = 180;
            System.out.println("La  velocidad no puede ser mayor que 180 por lo que la velocidad se ha ajustado a 180");
        }

        System.out.println("La velocidad se ha aumentado en " + velocidad);


        km = km + (velocidad * (cv * (int) ((Math.random()) * 10) + 1));

        System.out.println("Los km se han aumentado en " + km);
    }

    public void frenar(int velocidad) {

        this.velocidad -= velocidad;

        if (this.velocidad < 0) {
            this.velocidad = 0;
            System.out.println("La  velocidad no puede ser menor que 0 por lo que la velocidad se ha ajustado a 0");
        }

        System.out.println("La velocidad se ha disminuido en " + velocidad);
    }

    public void parar() {
        this.velocidad = 0;
    }

    public void resetear() {
        this.velocidad = 0;
        this.km = 0.0;
    }

    public void mostrarDatos() {
        System.out.println("Los datos del coche son:");
        System.out.printf("Matricula: %s    Modelo: %s    Caballos: %d  Velocidad: %d   Kilometros: %.2f\n\n",
                matricula, modelo, cv, velocidad, km);
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

}
