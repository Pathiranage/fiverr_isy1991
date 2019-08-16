import { Injectable } from '@angular/core';
import { Empresa } from './empresa';
import { Observable } from 'rxjs/Observable';
import { of} from 'rxjs/observable/of';
import { HttpClient, HttpHeaders, HttpRequest, HttpEvent } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { _throw as throwError } from 'rxjs/observable/throw';
import {AuthService} from '../usuarios/auth.service';



@Injectable()
export class EmpresaService {
  private urlEndPoint: string = 'http://localhost:8080/api/empresas';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient, private router: Router){}


// Obtenemos las empresas
  getEmpresas():Observable<Empresa[]>{
    return this.http.get<Empresa[]>(this.urlEndPoint);
  }

  create(empresa: Empresa): Observable<Empresa>{
    return this.http.post<Empresa>(this.urlEndPoint, empresa, {headers: this.httpHeaders} )
  }

  getEmpresa(id): Observable<Empresa>{
    return this.http.get<Empresa>(`${this.urlEndPoint}/${id}`)
  }

  update(empresa: Empresa): Observable<Empresa>{
    return this.http.put<Empresa>(`${this.urlEndPoint}/${empresa.id}` , empresa, {headers: this.httpHeaders})
  }

  delete(id: number): Observable<Empresa>{
    return this.http.delete<Empresa>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders})
  }

  deletetodo(id: number): Observable<void>{
    return this.http.delete<void>(`${this.urlEndPoint}/${id}`);
  }
}
