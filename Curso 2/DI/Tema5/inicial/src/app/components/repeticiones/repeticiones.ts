import { Component } from '@angular/core';
import Swal from 'sweetalert2'
import { usuario } from '../../model/usuario';

@Component({
  selector: 'app-repeticiones',
  standalone: false,
  templateUrl: './repeticiones.html',
  styleUrl: './repeticiones.css',
})
export class Repeticiones {

  usuarios: usuario[] = [new usuario('Borja', 'Martin', 30),
    new usuario('Maria', 'Lopez', 25),
    new usuario('Juan', 'Garcia', 28)
  ]
  nombreIntroducido: string = ''
  
  agregarUsuario() {
    
    if (this.nombreIntroducido.length == 0) {
      this.lanzarCuadro('Error', 'No hay datos en el campo', 'error')
    } else if (this.usuarios.find((p) => p.getNombre() == this.nombreIntroducido) != undefined) {
      this.lanzarCuadro('Error', 'El usuario ya esta en la lista', 'error')
    } else {
      this.usuarios.push()
    }
    }

    lanzarCuadro(titulo: string, texto: string, icono: string) {
    Swal.fire({
      title: titulo,
      text: texto
    })
  }

  }

