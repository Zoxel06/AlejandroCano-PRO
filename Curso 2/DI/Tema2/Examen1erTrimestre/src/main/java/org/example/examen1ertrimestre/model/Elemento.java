package org.example.examen1ertrimestre.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Elemento {

    private String nombre, categoria;
    private double precio;
    private String descripcion;
    private int id;

    @Override
    public String toString() {
        return "ID: " + id + " - Precio: " + precio;
    }
}
