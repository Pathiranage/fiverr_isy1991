import { Component, OnInit } from '@angular/core';
import {Producto} from './producto';
import {ProductoService} from './producto.service';
import {Router} from '@angular/router';
import swal from 'sweetalert2'

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html'
})
export class ProductosComponent implements OnInit {

  productos:Producto[]=[];
  producto: any;
  constructor( private _productoService: ProductoService, private router: Router){ }

  ngOnInit() {
    this._productoService.getProductos().subscribe(
      productos => this.productos = productos
    );
  }


  delete(producto: Producto): void{
    swal({
      title: 'Warning',
      text: `¿Estás seguro/a que deseas eliminar el producto ${producto.nombre}?`,
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
        this._productoService.delete(producto.id).subscribe(
          response => {
            this.productos = this.productos.filter(pro => pro !== producto)
            swal(
              'Producto eliminado',
              `Producto ${producto.nombre} eliminado con éxito`,
              'success'
            )
          }
        )

      }
    }
  )
}

}
