import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Libro } from './components/libro/libro';
import { Carrito } from './components/carrito/carrito';

const routes: Routes = [
  {path: 'libro', component: Libro},
  {path: 'carrito', component: Carrito},
  { path: '**', pathMatch: 'full', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
