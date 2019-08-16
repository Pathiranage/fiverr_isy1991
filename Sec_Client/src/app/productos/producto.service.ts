import { Injectable } from '@angular/core';
import {Empresa} from '../empresas/empresa'
import { Observable } from 'rxjs/Observable';
import { of} from 'rxjs/observable/of';
import { HttpClient, HttpHeaders, HttpRequest, HttpEvent } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { _throw as throwError } from 'rxjs/observable/throw';
import {AuthService} from '../usuarios/auth.service';
import {Producto} from './producto';
import {Version} from './version';

@Injectable()
export class ProductoService {

    private urlEndPoint: string = 'http://localhost:8080/api/productos';
    private urlExternalPointEmpresas: string = 'http://localhost:8080/api/empresas';
    private urlExternalPointVersion: string = 'http://localhost:8080/api/versiones';


    private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

    constructor(private http: HttpClient, private router: Router){}

    // Obtenemos las empresas
    getEmpresas():Observable<Empresa[]>{
      return this.http.get<Empresa[]>(this.urlExternalPointEmpresas);
    }
    // Obtenemos los productos
    getProductos():Observable<Producto[]>{
      return this.http.get<Producto[]>(this.urlEndPoint);
    }

    create(producto: Producto): Observable<Producto>{
      return this.http.post<Producto>(this.urlEndPoint, producto, {headers: this.httpHeaders} )
    }

    getProducto(id): Observable<Producto>{
      return this.http.get<Producto>(`${this.urlEndPoint}/${id}`)
    }

    update(producto: Producto): Observable<Producto>{
      return this.http.put<Producto>(`${this.urlEndPoint}/${producto.id}` , producto, {headers: this.httpHeaders})
    }

    delete(id: number): Observable<Producto>{
      return this.http.delete<Producto>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders})
    }

    // Añadir nueva version partiendo del producto seleccionado
    createVersion(version: Version): Observable<Version>{
      return this.http.post<Version>(this.urlExternalPointVersion, version, {headers: this.httpHeaders})
    }
};
