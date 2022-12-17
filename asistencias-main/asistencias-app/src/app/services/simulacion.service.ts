import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AsistenciaSimulacion } from '../models/asistencia-simulacion';
import { SimulacionAula } from '../models/simulacion-aula';

@Injectable({
  providedIn: 'root'
})
export class SimulacionService {

  private urlBackend: string = 'http://localhost:8001/api/estudiantes';

  constructor(private http: HttpClient) { }

  crearSimulacionAulas(simulacionAula: any): Observable<SimulacionAula> {
    return this.http.post<SimulacionAula>(`${this.urlBackend}/crear-simulacion-aula`, simulacionAula);
  }

  buscarSimulacionAulaPorId(idSimulacion: number): Observable<SimulacionAula> {
    return this.http.get<SimulacionAula>(`${this.urlBackend}/simulacion-aula-por-id/${idSimulacion}`);
  }

  listarSimulacionAulaPorGrado(gradoId: number): Observable<SimulacionAula[]> {
    return this.http.get<SimulacionAula[]>(`${this.urlBackend}/listar-aulas-simulacion/${gradoId}`);
  }

  registrarAsistenciaSimulacion(asistenciaSimulacion: AsistenciaSimulacion): Observable<any> {
    return this.http.put<any>(`${this.urlBackend}/registrar-asistencia`, asistenciaSimulacion);
  }

  registrarSalidaSimulacion(asistenciaSimulacion: AsistenciaSimulacion): Observable<any> {
    return this.http.put<any>(`${this.urlBackend}/registrar-salida`, asistenciaSimulacion);
  }
}
