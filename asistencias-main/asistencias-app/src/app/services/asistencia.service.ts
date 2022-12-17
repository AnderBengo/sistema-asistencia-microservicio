import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Asistencia } from '../models/asistencia';

@Injectable({
  providedIn: 'root'
})
export class AsistenciaService {

  private urlBackend: string = 'http://localhost:8003/api/asistencias';

  private _sendLoginStatusSource = new Subject<any>();
  loginStatus$ = this._sendLoginStatusSource.asObservable();

  constructor(private http: HttpClient) { }

  registrarAsistencia(asistencia: Asistencia): Observable<any> {
    return this.http.post<any>(`${this.urlBackend}/registrar-asistencia`, asistencia);
  }

  registrarSalida(asistencia: Asistencia): Observable<any> {
    return this.http.put<any>(`${this.urlBackend}/registrar-salida`, asistencia);
  }

  reporteAsistencia(clase_id: string): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}/reporte`);
  }

  buscarAsistenciaPorCodigoEstudiante(codigoEstudiante: string): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}/buscar-por-codigo/${codigoEstudiante}`);
  }

  public setUsuario(usuario: string): void {
    window.sessionStorage.removeItem('USUARIO');
    window.sessionStorage.setItem('USUARIO', usuario);
  }

  public getUsuario(): any {
    return sessionStorage.getItem('USUARIO');
  }

  sendLoginStatus( usuario: string | undefined) {
    this._sendLoginStatusSource.next(usuario);
  }

}
