import { Component } from '@angular/core';
import { AsistenciaService } from './services/asistencia.service';
import { AuxiliarService } from './services/auxiliar.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  usuario!: string | undefined;

  constructor(private asistenciaService: AsistenciaService, private auxiliarService: AuxiliarService) { 
    this.usuario = asistenciaService.getUsuario();
    this.asistenciaService.loginStatus$.subscribe(
      data => {
        this.usuario = data;
      }
    )
  }

  cerrarSesion(){
    this.auxiliarService.cerrarSesion();
    this.asistenciaService.sendLoginStatus(undefined);
  }
  
}
