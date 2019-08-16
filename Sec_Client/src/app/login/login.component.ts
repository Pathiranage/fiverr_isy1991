import {Component, OnInit} from '@angular/core';
import {Usuario} from '../usuarios/usuario';
import {AuthService} from '../usuarios/auth.service';
import {Router} from '@angular/router';
import swal from 'sweetalert2';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    titulo = ' Inicia sesión ';
    usuario: Usuario;

    constructor(private authService: AuthService, private router: Router) {
        this.usuario = new Usuario();
    }

    ngOnInit() {
        if (this.authService.isAuthenticated()) {

            this.router.navigate(['/home']);
            // console.log(this.usuario.password)
        }
    }

    login(): void {

        if (this.usuario.username == null || this.usuario.password == null) {
            swal('Error login', 'Username/password vacíos', 'error');
            return;
        }

        // console.log(this.usuario.password)
        // this.usuario.username = 'user1';
        this.usuario.password = '12345';
        this.authService.login(this.usuario).subscribe(response => {
                // console.log(response);

                const payload = JSON.parse(atob(response.access_token.split('.')[1]));
                //  console.log(payload);

                this.authService.guardarUsuario(response.access_token);
                this.authService.guardarToken(response.access_token);
                const usuario = this.authService.usuario;

                const roless = this.authService.usuario.roles[0];
                // console.log(roless)
                swal('Login', `Bienvenido/a ${usuario.nombre}, iniciaste sesión con éxito`, 'success');
                if (this.authService.usuario.roles[0] === 'ROLE_ADMIN') {

                    this.router.navigate(['/usuarios']);

                } else {
                    this.router.navigate(['/evaluaciones']);

                }

            }, err => {
                if (err.status === 400) {
                    swal('Error login', 'Usuario/clave incorrecto', 'error');
                }
            }
        );
    }
}
