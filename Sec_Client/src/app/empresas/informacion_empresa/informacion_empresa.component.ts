import { Component, OnInit } from '@angular/core';
import {Empresa} from '../empresa'
import {EmpresaService} from '../empresa.service'
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'informacion-empresa',
  templateUrl: './informacion_empresa.component.html'
})
export class InformacionComponent implements OnInit {

  empresa: Empresa;
  constructor(private empresaService: EmpresaService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params=>{
      let id:number= +params.get('id');
      if(id){
        this.empresaService.getEmpresa(id).subscribe(empresa =>{
            this.empresa=empresa;
        })
      }
    }

    );
  }

}
