package model;

import utils.*;

import java.util.Scanner;

public class Terreno {
static Scanner sc = new Scanner(System.in);
    private Casa casa;
    private double m2;
    private Orientacion orientacion;
    private double valoracion;

    public Terreno() {
    }

    public Terreno(double m2, Orientacion orientacion, double valoracion) {
        this.m2 = m2;
        this.orientacion = orientacion;
        this.valoracion = valoracion * orientacion.getRevalorizacion();
    }

    public void estaTerreno(Terreno terreno) throws TerrenoNoCreadoException{
        if (terreno == null){
            throw new TerrenoNoCreadoException("Para crear una casa tienes que haber creado un terreno antes");
        }
    }

    public void construirCasa(double m2){
        if (casa == null && this.m2 > m2){
            casa = new Casa();
        }
    }

    public void revalorizarTerreno(){
        if (orientacion.name().equalsIgnoreCase("norte") || orientacion.name().equalsIgnoreCase("sur")){
            this.valoracion = valoracion * orientacion.getRevalorizacion();
        } else if (orientacion.name().equalsIgnoreCase("este") || orientacion.name().equalsIgnoreCase("oeste")) {
            this.valoracion = valoracion * orientacion.getRevalorizacion();
        }
    }

    public void mostrarDatosTerreno(){
        System.out.println("m2 = " + m2);
        System.out.println("orientacion = " + orientacion);
        System.out.println("valoracion = " + valoracion);
    }

    public void mostrarDatosCasa(){
        System.out.println("M2 = " + casa.m2);
        System.out.println("Piscina = " + casa.piscina);
        System.out.println("Habitaciones = " + casa.habitaciones);
    }

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public double getM2() {
        return m2;
    }

    public void setM2(double m2) {
        this.m2 = m2;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(Orientacion orientacion) {
        this.orientacion = orientacion;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }


    public class Casa {

        private double m2;
        private boolean piscina;
        private int habitaciones;

        public Casa() {
        }

        public Casa(double m2) {
            this.m2 = m2;
            this.piscina = false;
            this.habitaciones = 0;
        }

        public void construirHabitacion(double m2){
            if (m2 > this.m2){
                System.out.println("Excepcion");
            } else {
                habitaciones++;
                this.m2 -= m2;
                System.out.println("Habitacion construida con exito");
            }
        }

        public void construirPiscina(){
            if (piscina) {
                System.out.println("Excepcion");
            } else {
                piscina = true;
                System.out.println("Piscina construida con exito");
            }
        }

        public void construirAnexo(double m2){
            if (m2 > Terreno.this.m2 - this.m2){
                System.out.println("Excepcion (no hay suficiente espacio)");
            } else {
                this.m2 += m2;
            }
        }

        public double getM2() {
            return m2;
        }

        public void setM2(double m2) {
            this.m2 = m2;
        }

        public boolean isPiscina() {
            return piscina;
        }

        public void setPiscina(boolean piscina) {
            this.piscina = piscina;
        }

        public int getHabitaciones() {
            return habitaciones;
        }

        public void setHabitaciones(int habitaciones) {
            this.habitaciones = habitaciones;
        }

    }

}

