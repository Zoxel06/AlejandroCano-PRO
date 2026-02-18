import { Injectable } from '@angular/core';
import { Libro } from '../model/libro';

@Injectable({
  providedIn: 'root',
})
export class Libros {
  libros: Libro[] = [
    ]

  getLibros(): Libro[] {
    return this.libros
  }

}
