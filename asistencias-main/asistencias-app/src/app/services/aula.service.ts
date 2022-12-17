import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Grado } from '../models/grado';

@Injectable({
  providedIn: 'root'
})
export class AulaService {

  private urlBackend: string = 'http://localhost:8002/api/aulas';

  constructor(private http: HttpClient) { }

  buscarAula(id: number): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}/buscar/${id}`);
  }

  obtenerAulas(): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}/obtener-aulas`);
  }

  obtenerGrados(): Observable<Grado[]> {
    return this.http.get<Grado[]>(`${this.urlBackend}/grados`);
  }
}
