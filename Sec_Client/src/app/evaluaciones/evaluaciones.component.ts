import {Component, OnInit} from '@angular/core';
import {Evaluacion} from './evaluacion';
import {EvaluacionService} from './evaluaciones.service';
import {Router} from '@angular/router';
import {Usuario} from '../usuarios/usuario';
import {UsuarioService} from '../usuarios/usuario.service';
import {AuthService} from '../usuarios/auth.service';
import swal from 'sweetalert2';

@Component({
    selector: 'app-evaluaciones',
    templateUrl: './evaluaciones.component.html'
})
export class EvaluacionesComponent implements OnInit {

    evaluaciones: Evaluacion[] = [];

    valor_ciberseguridad: number;
    usuario: Usuario = new Usuario();
    noEvaluaciones: boolean;


    constructor(private authService: AuthService,
                private evaluacionService: EvaluacionService,
                private usuarioService: UsuarioService,
                private router: Router) {
    }

    ngOnInit() {
        this.evaluacionService.getEvaluaciones()
            .subscribe( evaluaciones => {
                this.evaluaciones = evaluaciones;
                console.log(this.evaluaciones);
            });

    }


    delete(evaluacion: Evaluacion): void {
        swal({
            title: 'Warning',
            text: `¿Estás seguro/a que deseas eliminar la evaluacion del producto  ${evaluacion.producto.nombre}?`,
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
                if (result.value) {
                    this.evaluacionService.delete(evaluacion.id).subscribe(
                        response => {
                            this.evaluaciones = this.evaluaciones.filter(eva => eva !== evaluacion);
                            swal(
                                'Evaluacion eliminada',
                                `Evaluacion del producto ${evaluacion.producto.nombre} eliminada con éxito`,
                                'success'
                            );
                        }
                    );

                }
            }
        );
    }

}
