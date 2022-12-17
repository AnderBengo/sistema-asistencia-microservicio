import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AsistenciaService } from 'src/app/services/asistencia.service';
import { AuxiliarService } from 'src/app/services/auxiliar.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario: string = '';
  password: string = '';

  mostrarMensajeError: boolean = false;

  constructor(private auxiliarService: AuxiliarService, private asistenciaService: AsistenciaService, private router: Router) { }

  ngOnInit(): void {
  }

  login() {
    this.auxiliarService.login(this.usuario, this.password)
        .subscribe(response => {
          this.asistenciaService.setUsuario(response.data.usuario)
          this.asistenciaService.sendLoginStatus(response.data.usuario)
          sessionStorage.setItem('AUXILIAR', response.data.id)
          this.mostrarMensajeError = false;
          if(response.data.usuario == 'ADMIN'){
            this.router.navigate(['reporte-admin'])
          } else {
            this.router.navigate(['registrar-asistencia'])
          }

        }, err => {
          this.mostrarMensajeError = true;
        })
  }

}
