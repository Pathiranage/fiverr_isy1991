import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Producto} from '../productos/producto';
import {ProductoService} from '../productos/producto.service';
import {EvaluacionService} from './evaluaciones.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Empresa} from '../empresas/empresa';
import {Evaluacion} from './evaluacion';
import swal from 'sweetalert2';
import {AuthService} from '../usuarios/auth.service';
import {Usuario} from '../usuarios/usuario';

@Component({
    // tslint:disable-next-line:component-selector
    selector: 'app-evaluaciones_formulario',
    templateUrl: './evaluaciones_formulario.html'
})
export class EvaluacionesFormularioComponent implements OnInit {

    public evaluacion: { product: number, empresa: string, version: string, user: number };
    private evaluacionRes: Evaluacion;
    titulo_crear: string = 'Crear Evaluación';
    titulo_editar: string = 'Editar Evaluación';
    uploadForm: FormGroup;

    private urlEndPointCreate: string = 'http://localhost:8080/api/evaluaciones';


    private httpHeaders = new HttpHeaders({'Authorization': 'Bearer ' + localStorage.getItem('token')});


    productos: Producto[] = [];
    empresas: Empresa[] = [];
    versions: any[] = [];
    users: Usuario[] = [];

    constructor(private productoService: ProductoService,
                private formBuilder: FormBuilder,
                private httpClient: HttpClient,
                private evaluacionService: EvaluacionService,
                private authService: AuthService,
                private router: Router,
                private activatedRoute: ActivatedRoute) {
        this.evaluacion = {
            product: null,
            empresa: null,
            version: null,
            user: null,
        };
    }

    ngOnInit() {
        this.cargarEvaluacion();
        this.uploadForm = this.formBuilder.group({
            profile: ['']
        });
        this.evaluacionService.getProductos().subscribe(productos => {
            this.productos = productos;
            // console.log(this.productos);
        });
        this.evaluacionService.getEmpresas().subscribe(empresas => {
            this.empresas = empresas;
            // console.log(this.empresas);
        });
        this.evaluacionService.findUsersByRole('ROLE_USER')
            .subscribe(res => {
                this.users = res;
            });
    }

    cargarEvaluacion(): void {
        this.activatedRoute.params.subscribe(params => {
            let id = params['id'];
            if (id) {
                this.evaluacionService.getEvaluacion(id).subscribe(res => {
                    console.log(res);
                });
                console.log(this.httpHeaders);
            }

        });
    }


    // PENDIENTE DE IMPLEMENTAR

    update(): void {
        const product = new Producto();
        product.id = +this.evaluacion.product;
        this.evaluacionRes.producto = product;
        const user = new Usuario();
        user.id = this.evaluacion.user;
        this.evaluacionRes.usuarios = [user];
        this.evaluacionRes.version = this.evaluacion.version;
        this.httpClient.put<any>(
            'http://localhost:8080/api/evaluaciones/' + this.evaluacionRes.id,
            this.evaluacionRes,
            {headers: this.httpHeaders})
            .subscribe(
                (res) => {
                    swal('Evaluacion', 'Creada correctamente', 'success');
                    this.router.navigate(['/evaluaciones']);
                },
                (err) => console.log(err)
            );
    }

    onFileSelect(event) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            let file: File = fileList[0];
            let formData: FormData = new FormData();
            formData.append('file', file, file.name);
            this.httpHeaders.append('Content-Type', 'multiplart/form-data');
            this.httpHeaders.append('Accept', 'application/json');
            this.httpClient.post('http://localhost:8080/api/evaluaciones', formData, {headers: this.httpHeaders})
                .subscribe(
                    (data: any) => {
                        this.evaluacionRes = data.mensaje;
                        // console.log(this.evaluacionRes);
                    },
                    error => console.log('error')
                );

        }
    }

    /*
        update():void{
          this.empresaService.update(this.empresa).subscribe(empresa=>{
            this.router.navigate(['/empresas'])
          })
        }

        delete(producto: Producto): void{
          this.productoService.delete(producto.id).subscribe(
            response => {
              this.empresa.productos=this.empresa.productos.filter(pro => pro !== producto)
            }
          )
        }
    */
    compararProducto(o1: Producto, o2: Producto) {
        return o1 == null || o2 == null ? false : o1.id == o2.id;
    }

    setEmpresa() {
        this.evaluacion.empresa = this.productos.find(value => value.id === +this.evaluacion.product).empresa.nombre;
        this.evaluacionService.getAllVersionsByProductId(this.evaluacion.product)
            .subscribe(res => {
                this.versions = res;
            });
    }
}
