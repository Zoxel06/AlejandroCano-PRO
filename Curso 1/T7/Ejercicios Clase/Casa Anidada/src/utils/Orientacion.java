package utils;

public enum Orientacion {

    NORTE(1.1),
    SUR(1.1),
    ESTE(1.05),
    OESTE(1.05);

    double revalorizacion;

    Orientacion() {
    }

    Orientacion(double revalorizacion) {
        this.revalorizacion = revalorizacion;
    }

    public double getRevalorizacion() {
        return revalorizacion;
    }

    public void setRevalorizacion(double revalorizacion) {
        this.revalorizacion = revalorizacion;
    }
}
