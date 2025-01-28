package model;

public class Alumno {

    private Asignatura asignatura1;
    private Asignatura asignatura2;
    private Asignatura asignatura3;

    public Alumno() {
    }

    public Alumno(Asignatura asignatura1, Asignatura asignatura2, Asignatura asignatura3) {
        this.asignatura1 = asignatura1;
        this.asignatura2 = asignatura2;
        this.asignatura3 = asignatura3;
    }

    public Alumno(int identificador1, int identificador2, int identificador3) {
        this.asignatura1 = new Asignatura(identificador1);
        this.asignatura2 = new Asignatura(identificador2);
        this.asignatura3 = new Asignatura(identificador3);
    }

    public Asignatura getAsignatura1() {
        return asignatura1;
    }

    public void setAsignatura1(Asignatura asignatura1) {
        this.asignatura1 = asignatura1;
    }

    public Asignatura getAsignatura2() {
        return asignatura2;
    }

    public void setAsignatura2(Asignatura asignatura2) {
        this.asignatura2 = asignatura2;
    }

    public Asignatura getAsignatura3() {
        return asignatura3;
    }

    public void setAsignatura3(Asignatura asignatura3) {
        this.asignatura3 = asignatura3;
    }

}
