import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Usuario} from './usuario';
import swal from 'sweetalert2';

@Injectable()
export class AuthService {

    private _usuario: Usuario;
    private _token: string;

    constructor(private http: HttpClient) {

    }


    public get usuario(): Usuario {
        if (this._usuario != null) {
            return this._usuario;
        } else if (this._usuario == null && localStorage.getItem('usuario') != null) {
            this._usuario = JSON.parse(localStorage.getItem('usuario')) as Usuario;
            return this._usuario;
        }
        return new Usuario();
    }


    public get token(): string {
        if (this._token != null) {
            return this._token;
        } else if (this._token == null && localStorage.getItem('token') != null) {
            this._token = localStorage.getItem('item');
            return this._token;
        }
        return null;
    }


    login(usuario: Usuario): Observable<any> {

        const urlEndpoint = 'http://localhost:8080/oauth/token';
        const credenciales = btoa('angularapp' + ':' + '12345');
        const httpHeaders = new HttpHeaders({
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': 'Basic ' + credenciales
        });

        let params = new URLSearchParams();
        params.set('grant_type', 'password');
        params.set('username', usuario.username);
        params.set('password', usuario.password);
        //console.log(params.toString());

        return this.http.post<any>(urlEndpoint, params.toString(), {headers: httpHeaders});
    }

    guardarUsuario(accessToken: string): void {
        let payload = this.obtenerDatosToken(accessToken);
        console.log(payload);
        this._usuario = new Usuario();
        this._usuario.nombre = payload.nombre;
        this._usuario.apellido = payload.apellido;
        this._usuario.username = payload.user_name;
        this._usuario.password = payload.password;
        this._usuario.roles = payload.authorities;
        //  console.log(this._usuario)
        localStorage.setItem('usuario', JSON.stringify(this._usuario));
    }

    guardarToken(accessToken: string): void {
        this._token = accessToken;
        //console.log(accessToken)
        localStorage.setItem('token', accessToken);
    }

    obtenerDatosToken(accessToken: string): any {
        let datos_token: any;
        if (accessToken != null) {
            datos_token = JSON.parse(atob(accessToken.split('.')[1]));
            //  console.log(datos_token)
            return JSON.parse(atob(accessToken.split('.')[1]));
        }
        return null;
    }

    // Revisar
    isAuthenticated(): boolean {
        let payload = this.obtenerDatosToken(this.token);
        if (payload != null && payload.user_name && payload.user_name.length > 0) {
            return true;
        }
        return false;
    }

    hasRole(role: string): boolean {
        if (this.usuario.roles.includes(role)) {
            return true;
        }
        return false;
    }

    getRole(role: string): void {
        let roles = this.usuario.roles.includes(role);
    }

    logout(): void {
        this._token = null;
        this._usuario = null;
        swal('Login', `Sesión terminada`, 'success');
        localStorage.clear();


    }
}
