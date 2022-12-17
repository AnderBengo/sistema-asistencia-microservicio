import { Component, OnInit } from '@angular/core';
import { Asistencia } from 'src/app/models/asistencia';
import { Aula } from 'src/app/models/aula';
import { Estudiante } from 'src/app/models/estudiante';
import { AsistenciaService } from 'src/app/services/asistencia.service';
import { AulaService } from 'src/app/services/aula.service';

@Component({
  selector: 'app-reporte-admin',
  templateUrl: './reporte-admin.component.html',
  styleUrls: ['./reporte-admin.component.css']
})
export class ReporteAdminComponent implements OnInit {

  aulas: Aula[] = [];

  aulaSeleccionada!: Aula | undefined;

  
  estudiantesConAsistencia: Estudiante[] = [];
  estudiantesSinAsistencia: Estudiante[] = [];

  asistencias: Asistencia[] = [];

  constructor(private aulaSerivce:AulaService, private asistenciaService: AsistenciaService) { }

  ngOnInit(): void {
    this.aulaSerivce.obtenerAulas()
        .subscribe(response => {
          this.aulas = response.data;
          console.log(response)
        })
  }

  seleccionarAula(aula: Aula){
    this.aulaSeleccionada = aula;
    let estudiantes: Estudiante[] = this.aulaSeleccionada.estudiantes;

    estudiantes.forEach(e => {
      this.asistenciaService.buscarAsistenciaPorCodigoEstudiante(e.codigoEstudiante)
          .subscribe(response => {
            if(response.data){
              this.estudiantesConAsistencia.push(e);
              this.asistencias.push(response.data);
            } else {
              this.estudiantesSinAsistencia.push(e);
            }
          })
    })
  }

  mostrarAulas() {
    this.aulaSeleccionada = undefined;
    this.estudiantesConAsistencia = [];
    this.estudiantesSinAsistencia = [];
    this.asistencias = [];
  }



}
