import { Component, OnInit } from '@angular/core';
import { Asistencia } from 'src/app/models/asistencia';
import { Aula } from 'src/app/models/aula';
import { Auxiliar } from 'src/app/models/auxiliar';
import { Estudiante } from 'src/app/models/estudiante';
import { AsistenciaService } from 'src/app/services/asistencia.service';
import { AulaService } from 'src/app/services/aula.service';
import { AuxiliarService } from 'src/app/services/auxiliar.service';

@Component({
  selector: 'app-reporte-auxiliar',
  templateUrl: './reporte-auxiliar.component.html',
  styleUrls: ['./reporte-auxiliar.component.css']
})
export class ReporteAuxiliarComponent implements OnInit {

  estudiantesConAsistencia: Estudiante[] = [];
  estudiantesSinAsistencia: Estudiante[] = [];
  auxiliar!: Auxiliar;

  nombreAula: string = '';

  aulaSeleccionada!: Aula | undefined;

  listaDeAulas: Aula[] = [];
  asistencias: Asistencia[] = [];
  

  constructor(private asistenciaService: AsistenciaService, private aulaService: AulaService, private auxiliarService: AuxiliarService) { }

  ngOnInit(): void {
    console.log('EXITO')
    let usuarioId = sessionStorage.getItem('AUXILIAR');
    this.auxiliarService.buscarPorId(usuarioId)
        .subscribe(response => {
          console.log(response)
          this.auxiliar = response.data;
          this.listaDeAulas = this.auxiliar.aulas;
          // this.aulaService.buscarAula(response.data.aula.id)
          // .subscribe(responseAula => {
          //   console.log('responseAula', responseAula)
          //   let estudiantes: Estudiante[] = responseAula.data.estudiantes;
          //   this.auxiliar = responseAula.data.auxiliar;
          //   this.nombreAula = responseAula.data.nombre
  
          //   estudiantes.forEach(e => {
          //     this.asistenciaService.buscarAsistenciaPorCodigoEstudiante(e.codigoEstudiante)
          //         .subscribe(responseAsistencia => {
          //           if(responseAsistencia.data){
          //             this.estudiantesConAsistencia.push(e);
          //           } else {
          //             this.estudiantesSinAsistencia.push(e);
          //           }
          //         })
          //   })
  
          // })
        })

  }

  mostrarAula(aula: Aula) {
    let estudiantes: Estudiante[] = aula.estudiantes;
    console.log(aula)
    this.aulaSeleccionada = aula;

    

    estudiantes.forEach(e => {
      this.asistenciaService.buscarAsistenciaPorCodigoEstudiante(e.codigoEstudiante)
          .subscribe(responseAsistencia => {
            
            if(responseAsistencia.data){
              this.estudiantesConAsistencia.push(e);
              this.asistencias.push(responseAsistencia.data);
            } else {
              this.estudiantesSinAsistencia.push(e);
            }
          })
    })
  }

  listarAulas() {
    this.aulaSeleccionada = undefined;
    this.estudiantesConAsistencia = [];
    this.estudiantesSinAsistencia = [];
    this.asistencias = [];
  }



}
