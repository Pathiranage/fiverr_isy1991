import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { UsuarioService } from './usuario.service';
import {tap} from 'rxjs/operators';
import {AuthService} from '../usuarios/auth.service';
import {ActivatedRoute} from '@angular/router';
import swal from 'sweetalert2'

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
})
export class UsuariosComponent implements OnInit {

  usuarios: Usuario[];

  constructor(private usuarioService: UsuarioService, private authService: AuthService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.usuarioService.getUsuarios().subscribe(
       usuarios => this.usuarios = usuarios

    );


  }

  delete(usuario: Usuario): void{
    swal({
      title: 'Warning',
      text: `¿Estás seguro/a que deseas eliminar al usuario ${usuario.nombre} ${usuario.apellido}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si',
      cancelButtonText: 'No',
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      buttonsStyling: false,
      reverseButtons: true
    }).then((result) => {
      if(result.value){
        this.usuarioService.delete(usuario.id).subscribe(
          response => {
            this.usuarios = this.usuarios.filter(usu => usu !== usuario)
            swal(
              'Usuario elminado',
              `Usuario ${usuario.nombre} eliminado con éxito`,
              'success'
            )
          }
        )

      }
    }
  )
}
}
