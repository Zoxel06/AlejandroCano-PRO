package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProfesorExterno extends  Profesor{

    private double salarioMensual;

    public ProfesorExterno(String nombre, String dni, double salarioAnual, double salarioMensual) {
        super(nombre, dni, salarioAnual);
        this.salarioMensual = salarioMensual;
    }

    public double calcularSalario(){
        double salarioTotal;

        salarioTotal = getSalarioAnual() + (salarioMensual * 12);

        return salarioTotal;
    }
}
