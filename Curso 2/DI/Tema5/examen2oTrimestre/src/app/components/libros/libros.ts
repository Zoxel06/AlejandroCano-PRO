import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-libros',
  standalone: false,
  templateUrl: './libros.html',
  styleUrl: './libros.css',
})
export class Libros {
  

  constructor(private gestorRutas: Router){}

}
