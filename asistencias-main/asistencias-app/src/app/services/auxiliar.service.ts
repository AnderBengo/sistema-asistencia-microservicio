import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuxiliarService {

  private urlBackend: string = 'http://localhost:8002/api/auxiliares';

  constructor(private http: HttpClient, private router: Router) { }

  login(usuario: string, password: string): Observable<any> {
    let params = new HttpParams();
    params = params.set('usuario', usuario);
    params = params.set('password', password);
    return this.http.get<any>(`${this.urlBackend}/login`, {params: params});
  }

  buscarPorId(id: number | null | string): Observable<any> {
    return this.http.get<any>(`${this.urlBackend}/${id}`);
  }

  cerrarSesion() {
    window.sessionStorage.clear();
    this.router.navigate(['login'])
  }
}
