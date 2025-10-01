package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProfesorInterno extends Profesor {

    private int horas;
    private double precioHora;

    public ProfesorInterno(String nombre, String dni, double salarioAnual, int horas, double precioHora) {
        super(nombre, dni, salarioAnual);
        this.horas = horas;
        this.precioHora = precioHora;
    }

    public double calcularSalario(){
        double salarioTotal;

        salarioTotal = getSalarioAnual() + (horas * precioHora);

        return salarioTotal;
    }

}
