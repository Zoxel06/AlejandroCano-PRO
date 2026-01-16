import { Component, signal } from '@angular/core';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('inicial');
  nombre:string = "Alejandro";
  apellido:string = "Cano Caballero";
  curso:number = 2;
  nombreCurso:string = "Desarrollo de Aplicaciones Multiplataforma";
  nota:number|string = 0;

  pulsarComenzar(notaInput: string) {
    // escribir el mensaje y que ponga la nota introducida dentro del input
    // como puedo saber si notaInput es un numero?????
    /* if (isNaN(Number(notaInput))) {

    }else {

    } */
    //this.nota = notaInput;
    Swal.fire({
  title: 'Error!',
  text: 'Do you want to continue',
  icon: 'error',
  confirmButtonText: 'Cool'
})
  }

}
