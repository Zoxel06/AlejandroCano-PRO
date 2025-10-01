package model;

public class Motor {

    private int cv, aceite;

    public Motor(){}

    public Motor(int cv, int aceite) {
        this.cv = cv;
        this.aceite = aceite;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public int getAceite() {
        return aceite;
    }

    public void setAceite(int aceite) {
        this.aceite = aceite;
    }

}
