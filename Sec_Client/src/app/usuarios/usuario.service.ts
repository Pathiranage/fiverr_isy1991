import { Injectable } from '@angular/core';
import { Usuario } from './usuario';
import { Observable } from 'rxjs/Observable';
import { of} from 'rxjs/observable/of';
import { HttpClient, HttpHeaders, HttpRequest, HttpEvent } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { _throw as throwError } from 'rxjs/observable/throw';
import {AuthService} from '../usuarios/auth.service';
import swal from 'sweetalert2'
import {Roles} from '../usuarios/roles'

@Injectable()
export class UsuarioService {

  private urlEndPoint: string = 'http://localhost:8080/api/usuarios';
  private urlExternalPoint: string = 'http://localhost:8080/api/roles';


  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient, private router: Router,
  private authService: AuthService){}

  private agregarAuthorizationHeader(){
      let token = this.authService.token;

      if(token != null){
        return this.httpHeaders.append('Authorization', 'Bearer ' + token);
      }
      return this.httpHeaders;
  }

  private isNoAutorizado(e): boolean{
    if(e.status==401){

      if(this.authService.isAuthenticated()){
        this.authService.logout();

      }
      this.router.navigate(['/login'])
      return true;
    }

    if(e.status==403){
      this.router.navigate(['/home'])
      return true;
    }
    return false;
  }


  getUsuarios(): Observable<Usuario[]> {
    return this.http.get(this.urlEndPoint).pipe(
      tap(response =>{
        let usuarios = response as Usuario[];
      }),
      map(response => {
        let usuarios = response as Usuario[];
        return usuarios.map(usuario => {
          return usuario;
        });
      }
    ),
      )
  }


  getRoles():Observable<Roles[]>{
    return this.http.get<Roles[]>(this.urlExternalPoint, {headers: this.agregarAuthorizationHeader()});
  }


  create(usuario: Usuario) : Observable<Usuario>{
    console.log(usuario.apellido)
    console.log(usuario.id)
    console.log(usuario.nombre)
    console.log(usuario.password)
    console.log(usuario.roles)

    return this.http.post(this.urlEndPoint, usuario, {headers: this.agregarAuthorizationHeader()})
    .pipe(
      map((response: any) => response.usuario as Usuario),
        catchError(e => {
          if(this.isNoAutorizado(e)){
              return throwError(e);
          }
          if(e.status == 400){
            return throwError(e);
          }

          console.error(e.error.mensaje);
          return throwError(e);
        }

        )
    )
  }

  update(usuario: Usuario): Observable<any>{
    return this.http.put<any>(`${this.urlEndPoint}/${usuario.id}`, usuario, {headers: this.agregarAuthorizationHeader()}).pipe(
      catchError(e => {

        if(this.isNoAutorizado(e)){
            return throwError(e);
        }

        if(e.status==400){
          return throwError(e);
        }

        console.error(e.error.mensaje);
        return throwError(e);
      })
    )
  }

  getUsuario(id): Observable<Usuario>{
    return this.http.get<Usuario>(`${this.urlEndPoint}/${id}`, {headers: this.agregarAuthorizationHeader()}).pipe(
      catchError(e => {

        if(this.isNoAutorizado(e)){
            return throwError(e);
        }

        this.router.navigate(['/usuarios']);
        console.error(e.error.mensaje);
        return throwError(e);
      })
    );
  }


  delete(id: number): Observable<Usuario>{
    return this.http.delete<Usuario>(`${this.urlEndPoint}/${id}`, {headers: this.agregarAuthorizationHeader()}).pipe(
      catchError(e => {
        if(this.isNoAutorizado(e)){
            return throwError(e);
        }

        console.error(e.error.mensaje);
        return throwError(e);

      })
    )
  }

}
