import { Component, OnInit } from '@angular/core';
import {Usuario} from './usuario'
import {UsuarioService} from './usuario.service'
import {Router, ActivatedRoute} from '@angular/router'
import swal from 'sweetalert2'
import{Roles} from './roles'


@Component({
  selector: 'app-form',
  templateUrl: './formulario.component.html',
})
export class FormularioComponent implements OnInit {

  private usuario: Usuario = new Usuario()
  titulo_crear: string = "Crear Usuario";
  titulo_editar: string = "Editar Usuario";
  
  roles: Roles[];


  constructor(private usuarioService: UsuarioService,
  private router: Router,
  private activatedRoute: ActivatedRoute) {
  this.usuario = new Usuario();
 }

  ngOnInit() {
    this.cargarUsuario();
  }

  cargarUsuario(): void{
    this.activatedRoute.params.subscribe(params => {
      let id = +params['id']
      if(id){
        this.usuarioService.getUsuario(id).subscribe((usuario) => this.usuario = usuario)
      }
    });
        this.usuarioService.getRoles().subscribe(roles => this.roles = roles)

    //  this.productoService.getEmpresas().subscribe(empresas => this.empresas = empresas)
  }

  create(): void{
    this.usuarioService.create(this.usuario)
    .subscribe(
      usuario => {
      this.router.navigate(['/usuarios'])
      swal('Nuevo Usuario', `Usuario ${usuario.nombre} creado con éxito`, 'success')
    }
  );
  }

  update(): void{
    this.usuarioService.update(this.usuario).subscribe(usuario => {
    this.router.navigate(['/usuarios'])
    console.log(this.usuario.password)
      }
    )
  }

  compararRol(o1: Roles, o2: Roles){
    console.log("Entra")
    return o1 ==null || o2 ==null? false: o1.id==o2.id;
    }
}
