import { Component, signal } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('universoLibro');
  constructor(private gestorRutas: Router){}

  navegar(param:string){
    this.gestorRutas.navigate(['libro',param])
  }
}
