import {Component, OnInit} from '@angular/core';
import {Producto} from '../producto';
import {ProductoService} from '../producto.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Empresa} from '../../empresas/empresa';
import {Version} from '../version';
import swal from 'sweetalert2';

@Component({
    selector: 'app-formularioproducto',
    templateUrl: './formulario_producto.component.html'
})
export class FormularioProductoComponent implements OnInit {

    private producto: Producto = new Producto();
    titulo_crear: string = 'Crear Producto';
    titulo_editar: string = 'Editar Producto';


    empresas: Empresa[];

    errores: string[];

    constructor(private productoService: ProductoService, private router: Router,
                private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.activatedRoute.paramMap.subscribe(params => {
            let id = +params.get('id');
            if (id) {
                this.productoService.getProducto(id).subscribe((producto) => this.producto = producto);
            }
        });
        this.productoService.getEmpresas().subscribe(empresas => this.empresas = empresas);
    }

    create(): void {
        this.productoService.create(this.producto).subscribe(
            response => this.router.navigate(['/productos'])
        );
    }

    update(): void {
        this.productoService.update(this.producto).subscribe(producto => {
            this.router.navigate(['/productos']);
        });
    }

    compararEmpresa(o1: Empresa, o2: Empresa) {
        return o1 === null || o2 === null || o2 === undefined || o1.id === undefined || o2.id === undefined ? false : o1.id === o2.id;
    }

    addNewVersion() {
        if (this.producto.version === null || this.producto.version === undefined) {
            swal('Version Error', 'Invalid version', 'warning');
            return;
        }
        if (this.producto.versiones.length > 0) {
            const v = this.producto.versiones.find(value => value.nombre === this.producto.version);
            if (v !== null && v !== undefined) {
                swal('Version', 'Duplicate version', 'warning');
                return;
            }
        }
        const version = new Version();
        version.nombre = this.producto.version;
        this.producto.versiones.push(version);
    }

    setVersion(value: any) {
        this.producto.version = value;
    }
}
