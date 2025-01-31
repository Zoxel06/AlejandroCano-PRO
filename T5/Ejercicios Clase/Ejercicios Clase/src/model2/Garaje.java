package model2;

public class Garaje {


    private Coche cocheActual;
    private String averia;
    private int numeroCoches;

    public Garaje(){
    }

    public void aceptarCoche(Coche coche){
       if (cocheActual==null){
           cocheActual = coche;
       }
    }

    public void devolverCoche(Coche coche){

    }



}
