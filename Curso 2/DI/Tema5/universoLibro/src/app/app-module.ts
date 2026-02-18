import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Libro } from './components/libro/libro';
import { Carrito } from './components/carrito/carrito';
import { Detalle } from './components/detalle/detalle';
import { ImagenesPipe } from './pipes/imagenes-pipe';
import { Carta } from './components/carta/carta';

@NgModule({
  declarations: [
    App,
    Libro,
    Carrito,
    Detalle,
    ImagenesPipe,
    Carta
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideClientHydration(withEventReplay()),
  ],
  bootstrap: [App]
})
export class AppModule { }
