import { Component, signal } from '@angular/core';
import { Route, Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('examen2oTrimestre');

  constructor(private gestorRutas: Router){}

  navegar(param:string){
    this.gestorRutas.navigate(['buscar',param])
  }

}
