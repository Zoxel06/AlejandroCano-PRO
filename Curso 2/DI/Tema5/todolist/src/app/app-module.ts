import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Agregar } from './agregar/agregar';
import { Buscar } from './componentes/buscar/buscar';
import { Listar } from './componentes/listar/listar';
import { Carta } from './components/carta/carta';
import { ImagenesPipePipe } from './pipes/imagenes-pipe-pipe';
import { ImagenesPipe } from './pipes/imagenes-pipe';
import { Detail } from './components/detail/detail';

@NgModule({
  declarations: [
    App,
    Agregar,
    Buscar,
    Listar,
    Carta,
    ImagenesPipePipe,
    ImagenesPipe,
    Detail
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
  ],
  bootstrap: [App]
})
export class AppModule { }
