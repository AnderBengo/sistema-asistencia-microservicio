import { Component, OnInit } from '@angular/core';
import { Asistencia } from 'src/app/models/asistencia';
import { Estudiante } from 'src/app/models/estudiante';
import { AsistenciaService } from 'src/app/services/asistencia.service';

@Component({
  selector: 'app-registrar-salida',
  templateUrl: './registrar-salida.component.html',
  styleUrls: ['./registrar-salida.component.css']
})
export class RegistrarSalidaComponent implements OnInit {

  asistencia!: Asistencia | undefined;
  estudiante: Estudiante = new Estudiante();

  mensaje!: string | undefined;

  mostrarDatosAsistencia: boolean = false;

  codigoRespuesta!: string | undefined;

  constructor(private asistenciaService: AsistenciaService) { }

  ngOnInit(): void {
  }

  registrarSalida() {
    this.asistencia = new Asistencia();
    this.asistencia.estudiante = this.estudiante;
    this.asistencia.ingresoConfirmado=true;
    this.asistencia.salidaConfirmado=true;

    this.mensaje = undefined;
    this.codigoRespuesta = undefined;
    this.mostrarDatosAsistencia = false;

    this.asistenciaService.registrarSalida(this.asistencia)
    .subscribe({
      next: (response) => {
        this.asistencia = response.data;
        console.log(response.code);
        this.mensaje = response.message;
        this.mostrarDatosAsistencia = true;
        this.codigoRespuesta = response.code;
      },
      error: (err) => {
        this.mostrarDatosAsistencia = true;
        this.asistencia = err.error.data;
        this.mensaje = err.error.message;
      }
    })
  }

}
