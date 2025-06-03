package model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Profesor  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre, dni;
    private double salarioAnual;

}
