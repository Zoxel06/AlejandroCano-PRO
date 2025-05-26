package model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coche implements Serializable  {
    private String matricula, marca, modelo,color;
    private int precio;

    public void mostrarDatos(){
        System.out.println("matricula = " + matricula);
        System.out.println("marca = " + marca);
        System.out.println("modelo = " + modelo);
        System.out.println("color = " + color);
        System.out.println("precio = " + precio);
    }

}
