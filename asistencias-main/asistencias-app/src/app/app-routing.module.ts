import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RegistrarAsistenciaComponent } from './components/registrar-asistencia/registrar-asistencia.component';
import { RegistrarSalidaComponent } from './components/registrar-salida/registrar-salida.component';
import { ReporteAuxiliarComponent } from './components/reporte-auxiliar/reporte-auxiliar.component';
import { LoginComponent } from './components/login/login.component';
import { ReporteAdminComponent } from './components/reporte-admin/reporte-admin.component';
import { SimulacionAula } from './models/simulacion-aula';
import { SimulacionAulaComponent } from './components/simulacion-aula/simulacion-aula.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'registrar-asistencia', component: RegistrarAsistenciaComponent},
  {path: 'registrar-salida', component: RegistrarSalidaComponent},
  {path: 'reporte-auxiliar', component: ReporteAuxiliarComponent},
  {path: 'reporte-admin', component: ReporteAdminComponent},
  {path: 'login', component: LoginComponent},
  {path: 'simulacion', component: SimulacionAulaComponent}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
