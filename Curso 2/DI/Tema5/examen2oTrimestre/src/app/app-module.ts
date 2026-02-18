import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { FormsModule } from '@angular/forms';
import { Libros } from './components/libros/libros';
import { Carrito } from './components/carrito/carrito';
import { Detalles } from './components/detalles/detalles';
import { ImagenesPipe } from './pipes/imagenes-pipe';
import { Carta } from './components/carta/carta';

@NgModule({
  declarations: [
    App,
    Libros,
    Carrito,
    Detalles,
    ImagenesPipe,
    Carta,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideClientHydration(withEventReplay()),
  ],
  bootstrap: [App]
})
export class AppModule { }
