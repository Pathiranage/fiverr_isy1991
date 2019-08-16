import { Component, OnInit } from '@angular/core';
import {Empresa} from './empresa';
import {EmpresaService} from './empresa.service'
import {Router, ActivatedRoute} from '@angular/router'
import {Producto} from '../productos/producto'
import {ProductoService} from '../productos/producto.service'


@Component({
  selector: 'app-formuempresas',
  templateUrl: './empresas_formulario.component.html'
})
export class FormuEmpresasComponent implements OnInit {

  private empresa: Empresa = new Empresa()
  titulo_crear: string = "Crear Empresa";
  titulo_editar: string = "Editar Empresa";

  constructor(private empresaService: EmpresaService, private router: Router, private productoService: ProductoService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.cargarEmpresa()
  }

  cargarEmpresa(): void{
    this.activatedRoute.params.subscribe(params =>{
      let id = params['id']
      if(id){
        this.empresaService.getEmpresa(id).subscribe((empresa) => this.empresa = empresa)
      }

    })
  }

   create(): void{
    this.empresaService.create(this.empresa).subscribe(
      response => this.router.navigate(["/empresas"])
    )
  }

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

}
