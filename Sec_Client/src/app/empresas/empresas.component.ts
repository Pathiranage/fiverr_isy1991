import { Component, OnInit } from '@angular/core';
import {Empresa} from './empresa';
import {EmpresaService} from './empresa.service'
import {Producto} from '../productos/producto'
import {ProductoService} from '../productos/producto.service'
import swal from 'sweetalert2'

@Component({
  selector: 'app-empresas',
  templateUrl: './empresas.component.html'
})
export class EmpresasComponent implements OnInit {

  empresas: Empresa[] = [];
  private empresa: Empresa = new Empresa()

  constructor(private empresaService: EmpresaService, private productoService: ProductoService) { }

  ngOnInit() {
    this.empresaService.getEmpresas().subscribe(
      empresas => this.empresas = empresas
    );
  }

  delete(empresa: Empresa): void{
    swal({
      title: 'Warning',
      text: `¿Estás seguro/a que deseas eliminar la empresa ${empresa.nombre}?`,
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
      if(result.value){
        this.empresaService.delete(empresa.id).subscribe(
          response => {
            this.empresas = this.empresas.filter(emp => emp !== empresa)
            swal(
              'Empresa elminada',
              `Empresa ${empresa.nombre} eliminada con éxito`,
              'success'
            )
          }
        )

      }
    }
  )
}


}
