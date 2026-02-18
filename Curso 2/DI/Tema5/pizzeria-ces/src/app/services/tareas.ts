import { Injectable } from '@angular/core';
import { tarea } from '../model/tarea';

@Injectable({
  providedIn: 'root',
})
export class Tareas {
  
  tareas: tarea[] = [
    {
      id: 1,
      nombre: 't1',
      responsable: 'R1',
      items: [],
      fecha: '',
      prioridad: 1,
      descipcion: 'Ejemplo t1'
    }
  ]

}
