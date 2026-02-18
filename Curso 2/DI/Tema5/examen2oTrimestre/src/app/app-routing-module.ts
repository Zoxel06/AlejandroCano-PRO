import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Libros } from './services/libros';
import { Carrito } from './components/carrito/carrito';

const routes: Routes = [
  {path: 'libro', component: Libros},
  { path: 'carrito/:id', component:  Carrito},
  { path: '**', pathMatch: 'full', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
