import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { RegistrarAsistenciaComponent } from './components/registrar-asistencia/registrar-asistencia.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegistrarSalidaComponent } from './components/registrar-salida/registrar-salida.component';
import { ReporteAuxiliarComponent } from './components/reporte-auxiliar/reporte-auxiliar.component';
import { LoginComponent } from './components/login/login.component';
import { ReporteAdminComponent } from './components/reporte-admin/reporte-admin.component';
import { SimulacionAulaComponent } from './components/simulacion-aula/simulacion-aula.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrarAsistenciaComponent,
    RegistrarSalidaComponent,
    ReporteAuxiliarComponent,
    LoginComponent,
    ReporteAdminComponent,
    SimulacionAulaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
