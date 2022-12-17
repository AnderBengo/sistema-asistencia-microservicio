import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AsistenciaSimulacion } from 'src/app/models/asistencia-simulacion';
import { Estudiante } from 'src/app/models/estudiante';
import { Grado } from 'src/app/models/grado';
import { SimulacionAula } from 'src/app/models/simulacion-aula';
import { SimulacionAulaItem } from 'src/app/models/simulacion-aula-item';
import { AulaService } from 'src/app/services/aula.service';
import { SimulacionService } from 'src/app/services/simulacion.service';

@Component({
  selector: 'app-simulacion-aula',
  templateUrl: './simulacion-aula.component.html',
  styleUrls: ['./simulacion-aula.component.css']
})
export class SimulacionAulaComponent implements OnInit {

  simulacionAula: SimulacionAula = new SimulacionAula();

  simulacionesAula: SimulacionAula[] = [];

  grados: Grado[] = [];

  gradoSeleccionado!: Grado;

  formularioSimulacion!: FormGroup;

  simulacionesItem: SimulacionAulaItem[] = [];

  creadoStatus: boolean = false;

  cantidadEstudiantes: number = 0;

  asistenciasSimulacion: AsistenciaSimulacion[] = [];

  listaAsistenciaConfirmada: AsistenciaSimulacion[] = [];
  listaAsistenciaNoConfirmada: AsistenciaSimulacion[] = [];

  estudiante: Estudiante = new Estudiante();

  asistencia: AsistenciaSimulacion = new AsistenciaSimulacion();

  mensaje!: string | undefined;

  mostrarDatosAsistencia: boolean = false;
  codigoRespuesta!: string | undefined;

  ingresoAsistencia: boolean = false;
  salidaAsistencia: boolean = false;

  constructor(private simulacionServie: SimulacionService, private aulaService: AulaService) { }

  ngOnInit(): void {
    this.aulaService.obtenerGrados().subscribe(response => this.grados = response);
    this.inicializarFormGroup();
  }

  habilitarAsistencia() {

    this.salidaAsistencia = false;
    this.ingresoAsistencia = true;
    this.asistencia = new AsistenciaSimulacion();
    this.estudiante = new Estudiante();
    this.mostrarDatosAsistencia = false;
    this.mensaje = undefined;
  }

  habilitarSalida() {
    this.salidaAsistencia = true;
    this.ingresoAsistencia = false;
    this.asistencia = new AsistenciaSimulacion();
    this.estudiante = new Estudiante();
    this.mostrarDatosAsistencia = false;
    this.mensaje = undefined;
  }

  cerrarAsistencias() {
    this.salidaAsistencia = false;
    this.ingresoAsistencia = false;
    this.asistencia = new AsistenciaSimulacion();
    this.estudiante = new Estudiante();
    this.mostrarDatosAsistencia = false;
    this.mensaje = undefined;
  }

  registrarAsistencia() {
    this.asistencia.estudiante = this.estudiante;
    this.simulacionServie.registrarAsistenciaSimulacion(this.asistencia)
        .subscribe({
          next: (response) => {
            this.asistencia = response.data;
            console.log(response.code);
            this.mensaje = response.message;
            this.mostrarDatosAsistencia = true;
            this.codigoRespuesta = response.code;
          },
          error: (err) => {
            if(err.status != 404) {
              this.asistencia = err.error.data;
            }
            this.mostrarDatosAsistencia = true;
            this.mensaje = err.error.message;
            this.codigoRespuesta = err.code;
            console.log(err)
          }
        })

  }

  registrarSalida() {
    this.asistencia.estudiante = this.estudiante;
    this.simulacionServie.registrarSalidaSimulacion(this.asistencia)
        .subscribe({
          next: (response) => {
            this.asistencia = response.data;
            console.log(response.code);
            this.mensaje = response.message;
            this.mostrarDatosAsistencia = true;
            this.codigoRespuesta = response.code;
          },
          error: (err) => {
            if(err.status != 404) {
              this.asistencia = err.error.data;
            }
            this.mostrarDatosAsistencia = true;
            this.mensaje = err.error.message;
            this.codigoRespuesta = err.code;
            console.log(err)
          }
        })
  }

  crearSimulacion() {
    this.simulacionServie.crearSimulacionAulas(this.formularioSimulacion.value)
        .subscribe(response => {
          this.limpiarForm();
          this.creadoStatus = true;
        })
  }

  buscarSimulacionesPorGrado() {
    this.simulacionesAula = [];
    this.cantidadEstudiantes = 0;

    if(this.gradoSeleccionado){
      this.calcularEstudiantes(this.gradoSeleccionado)
      this.gradoId?.setValue(this.gradoSeleccionado.id);
      this.simulacionServie.listarSimulacionAulaPorGrado(this.gradoSeleccionado.id)
      .subscribe(response => this.simulacionesAula = response);
    }


  }

  cargarItems(simulacion: SimulacionAula) {
    this.simulacionesItem = simulacion.itemsSimulacion;
  }

  limpiarItems(){
    this.simulacionesItem = [];
    this.salidaAsistencia = false;
    this.ingresoAsistencia = false;
    this.cerrarModalAsistneciasSimulacion();
    this.asistencia = new AsistenciaSimulacion();
    this.estudiante = new Estudiante();
    this.mostrarDatosAsistencia = false;
    this.mensaje = undefined;
  }

  calcularEstudiantes(grado: Grado){
    grado.aulas.forEach(a => {
      this.cantidadEstudiantes = this.cantidadEstudiantes + a.estudiantes.length;
    })
  }

  inicializarFormGroup() {
    this.formularioSimulacion = new FormGroup({
      nombre: new FormControl('', [Validators.required, Validators.minLength(3)]),
      fechaInicio: new FormControl('', [Validators.required]),
      fechaFin: new FormControl('', [Validators.required]),
      cantidadSimulacion: new FormControl('', [Validators.required, Validators.pattern("^[0-9]*$")]),
      gradoId: new FormControl()
    })
  }

  limpiarForm() {
    this.formularioSimulacion.reset();
    
    this.creadoStatus = false;
  }

  cerrarModalAsistneciasSimulacion(){
    this.asistenciasSimulacion = [];
    this.listaAsistenciaConfirmada = [];
    this.listaAsistenciaNoConfirmada = [];
  }

  cargarAsistenciasSimulacion(asistenciasSimulacion: AsistenciaSimulacion[]){
    this.asistenciasSimulacion = [];
    console.log(asistenciasSimulacion)
    this.asistenciasSimulacion = asistenciasSimulacion;

    this.asistenciasSimulacion.forEach(a => {
      if(a.ingresoConfirmado){
        this.listaAsistenciaConfirmada.push(a)
      }else {
        this.listaAsistenciaNoConfirmada.push(a)
      }
    })

    // asistenciasSimulacion.forEach(e => {
    //   this.asistenciaService.buscarAsistenciaPorCodigoEstudiante(e.codigoEstudiante)
    //       .subscribe(response => {
    //         if(response.data){
    //           this.estudiantesConAsistencia.push(e);
    //           this.asistencias.push(response.data);
    //         } else {
    //           this.estudiantesSinAsistencia.push(e);
    //         }
    //       })
    // })
  }

  get nombre() { return this.formularioSimulacion.get('nombre'); }

get fechaInicio() { return this.formularioSimulacion.get('fechaInicio'); }
get fechaFin() { return this.formularioSimulacion.get('fechaFin'); }

get cantidadSimulacion() { return this.formularioSimulacion.get('cantidadSimulacion'); }
get gradoId() { return this.formularioSimulacion.get('gradoId'); }

}
