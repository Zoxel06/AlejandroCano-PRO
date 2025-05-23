package model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Coche implements Serializable  {
    private static Long serialVersionUID = 1L;
    private String matricula, marca, modelo,color;
    private int id, precio;

    public void mostrarDatos(){
        System.out.println("id = " + id);
        System.out.println("matricula = " + matricula);
        System.out.println("marca = " + marca);
        System.out.println("modelo = " + modelo);
        System.out.println("color = " + color);
        System.out.println("precio = " + precio);
    }

}
