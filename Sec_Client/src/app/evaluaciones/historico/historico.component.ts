import { Component, OnInit,  ViewChild, Input } from '@angular/core';
import {Evaluacion} from '../evaluacion';
import {EvaluacionService} from '../evaluaciones.service';
import {Router} from '@angular/router';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { Color, BaseChartDirective, Label } from 'ng2-charts';
import {Charateristics} from '../evaluacion/charateristics';
import { Eveproperties} from './eveproperties';
import { Producto } from '../../productos/producto';
/*

/**/
@Component({
  selector: 'app-historico',
  templateUrl: './historico.component.html'
})


export class HistoricoComponent /*implements OnInit*/ {


  evaluaciones:Evaluacion[]=[];
  evaluacion:any ={};
  charasteristics: Charateristics[] = [];
  productId:number;
  evalId:number;
  counter1:number = 0;
  counter2:number = 0;
  counter3:number =0;
  counter4:number = 0;
  counter5:number = 0;
  eveprop: Eveproperties[]=[
    // {id: 0,
    // createAt: "",
    // version: "",
    // fecha_evaluacion : "",
    // valor_ciberseguridad: "",
    // valor_cump: 0,
    // valor_conf: 0,
    // valor_traz: 0,
    // valor_disp: 0,
    // valor_recup: 0,
    // producto: null}
  ];

  mostrarcump = false;
  mostrarconf = false;
  mostrartraz = false;
  mostrardisp = false;
  mostrarrecu = false;
  mostrargeneral=false;
  mostrargenbasic=false;
  productname: String = "";
  len:any = 0;
  count:number = 0;
  flag:boolean = false;
  //i:number;

  constructor( private _evaluacionService: EvaluacionService, private router: Router){




  }

  ngOnInit() {


    this._evaluacionService.getEvaluaciones()
    .subscribe(res => {
        console.log( res);
        this.evaluaciones = res;
        this.len = this.evaluaciones.length;
        this.eveprop =[];
        this.charasteristics =[];
        // this.lineChartData = [];
        // this.lineChartLabels = [];

        for( let i=0;i<this.len; i++){
          this.eveprop[i] = new Eveproperties();
          this.eveprop[i].createAt = this.evaluaciones[i].createAt;
          // this.lineChartLabels[i] = this.eveprop[i].createAt;
          console.log("createdat = " + this.eveprop[i].createAt);
         // this.eveprop[i].producto.version = this.evaluaciones[i].producto.version;
         this.eveprop[i].producto = new Producto();
         this.eveprop[i].producto.version = this.evaluaciones[i].producto.version;
          this.eveprop[i].producto.id = this.evaluaciones[i].producto.id;
           this.productname =this.evaluaciones[i].producto.nombre;
          if(this.eveprop[i].producto.id == this.productId){
            this.lineChartLabels[this.lineChartLabels.length] = this.eveprop[i].createAt;
            this.flag = true;

          }
           if(this.evaluaciones[i].macrocharacteristic==null)
           {
           this.eveprop[i].valor_ciberseguridad = null;
           }else{
            this.eveprop[i].valor_ciberseguridad = this.evaluaciones[i].macrocharacteristic.value;
           }
          this.charasteristics =[];
          this.loadCharacter(this.evaluaciones[i].id, i,this.flag);

        }
        console.log("len = " + this.len)
        console.log(this.eveprop);
    });

     //console.log("lens = " + this.evaluaciones.length);

    this.evalId = this._evaluacionService.getterEvalId();
    this.productId = this._evaluacionService.getterProductId();
    console.log("Productid = " + this.productId);
    // console.log("evalId = " + this.evalId);
    // this.loadCharacter(this.evalId);


  }

  loadCharacter(id:any,i,flag){
    console.log("Id caracteristica = " + id);
    this._evaluacionService.getCharasteristicsByEvaluacionesId(id)
    .subscribe(value => {
        this.charasteristics = value;
        console.log("caracteristica =");

        this.eveprop[i].valor_cump = this.charasteristics[0].value;
          this.eveprop[i].valor_conf = this.charasteristics[1].value;
          this.eveprop[i].valor_traz = this.charasteristics[2].value;
          this.eveprop[i].valor_disp = this.charasteristics[3].value;
          this.eveprop[i].valor_recup = this.charasteristics[4].value;
          if(flag == true){
          this.lineChartData[0].data[this.count] = this.charasteristics[0].value;
          this.lineChartData[1].data[this.count] = this.charasteristics[1].value;
          this.lineChartData[2].data[this.count] = this.charasteristics[2].value;
          this.lineChartData[3].data[this.count] = this.charasteristics[3].value;
          this.lineChartData[4].data[this.count] = this.charasteristics[4].value;
          console.log(this.charasteristics);
          this.count++;
          this.setChartValues(flag);
          this.flag = false;
          }
        console.log(value);
    });
  }

  verHistoricoEvaluacion(index:number){
    this.router.navigate(['evaluaciones/historico', index]);
    console.log("Index = " + index);
  }

  verEvaluacion(index:number){
    this.router.navigate(['evaluaciones/evaluacion', index]);
  }

// GRAFICA PARA LOS VALORES DE CIBERSEGURIDAD

  public lineChartData: ChartDataSets[] = [
    { data: [2, 2, 5], label: 'CUMP' },
    { data: [2, 3, 5], label: 'CONF' },
    { data: [3, 3, 3], label: 'TRAZ' },
    { data: [4, 5, 5], label: 'DISP' },
    { data: [2, 2, 2], label: 'RECUP' }
  ];

// GRAFICA PARA LOS VALORES DE LAS PROPIEDADES

// CUMPLIMIENTO
  public lineChartDataCump: ChartDataSets[] = [
    { data: [52, 39, 82], label: 'Cumplimiento Normativo de Valor y/o Formato' },
    { data: [62, 23, 95], label: 'Cumplimiento Normativo Debido a la Tecnolgoía' }
  ];

  // CONFIDENCIALIDAD
    public lineChartDataConf: ChartDataSets[] = [
      { data: [32, 43, 73], label: 'Uso de Encriptación' },
      { data: [71, 31, 82], label: 'No Vulnerabilidad' }

    ];

// TRAZABILIDAD
    public lineChartDataTraz: ChartDataSets[] = [
      { data: [42, 57, 72], label: 'Trazabilidad del Acceso a los Datos' },
      { data: [78, 79, 92], label: 'Trazabilidad de los Valores de Datos' }
    ];

// DISPONIBILIDAD
    public lineChartDataDisp: ChartDataSets[] = [
      { data: [59, 52, 73], label: 'Ratio de Disponibilidad de Datos' },
      { data: [72, 31, 92], label: 'Probabilidad de Disponibilidad de Datos' },
      { data: [79, 72, 93], label: 'Disponibilidad de Elementos de Arquitectura' }
    ];

// RECUPERABILIDAD
    public lineChartDataRecup: ChartDataSets[] = [
      { data: [44, 57, 83], label: 'Ratio de Recuperabilidad de Datos' },
      { data: [79, 36, 93], label: 'Backup Periódico' },
      { data: [84, 89, 99], label: 'Recuperabilidad de Arquitectura' }
    ];

    // TODAS
        public lineChartDataGeneral: ChartDataSets[] = [
          { data: [], label: 'Cumplimiento Normativo de Valor y/o Formato' },
          { data: [], label: 'Cumplimiento Normativo Debido a la Tecnolgoía' },
          { data: [], label: 'Uso de Encriptación' },
          { data: [], label: 'No Vulnerabilidad' },
          { data: [], label: 'Trazabilidad del Acceso a los Datos' },
          { data: [], label: 'Trazabilidad de los Valores de Datos' },
          { data: [], label: 'Ratio de Disponibilidad de Datos' },
          { data: [], label: 'Probabilidad de Disponibilidad de Datos' },
          { data: [], label: 'Disponibilidad de Elementos de Arquitectura' },
          { data: [], label: 'Ratio de Recuperabilidad de Datos' },
          { data: [], label: 'Backup Periódico' },
          { data: [], label: 'Recuperabilidad de Arquitectura' }
        ];

  public lineChartLabels: Label[] = [];


  public lineChartOptionsCar: (ChartOptions & { annotation: any }) = {
    responsive: true,
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      xAxes: [{}],
      yAxes: [
        {
          id: 'y-axis-0',
          position: 'left',
          gridLines: {
			color: 'rgba(255,0,0,0.3)',
		  },
		  ticks: {
			beginAtZero: true,
			min: 1,
			max: 5,
			stepSize: 1
		  },
        },
      ]
    },
    annotation: {
      annotations: [
        {
          type: 'line',
          mode: 'vertical',
          scaleID: 'x-axis-0',
          value: 'March',
          borderColor: 'orange',
          borderWidth: 2,
          label: {
            enabled: true,
            fontColor: 'orange',
            content: 'LineAnno'
          }
        },
      ],
    },
  };

  public lineChartOptionsProp: (ChartOptions & { annotation: any }) = {
    responsive: true,
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      xAxes: [{}],
      yAxes: [
        {
          id: 'y-axis-0',
          position: 'left',
          gridLines: {
			color: 'rgba(255,0,0,0.3)',
		  },
		  ticks: {
			beginAtZero: true,
			min: 0,
			max: 100,
			stepSize: 10
		  },
        },
      ]
    },
    annotation: {
      annotations: [
        {
          type: 'line',
          mode: 'vertical',
          scaleID: 'x-axis-0',
          value: 'March',
          borderColor: 'orange',
          borderWidth: 2,
          label: {
            enabled: true,
            fontColor: 'orange',
            content: 'LineAnno'
          }
        },
      ],
    },
  };

  public lineChartColors: Color[] = [
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // red
      backgroundColor: 'rgba(255,0,0,0.3)',
      borderColor: 'red',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // dark grey
      backgroundColor: 'rgba(77,83,96,0.2)',
      borderColor: 'rgba(77,83,96,1)',
      pointBackgroundColor: 'rgba(77,83,96,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(77,83,96,1)'
    },
  ];
  public lineChartLegend = true;
  public lineChartType = 'line';

  @ViewChild(BaseChartDirective) chart: BaseChartDirective;

  public randomize(): void {
    for (let i = 0; i < this.lineChartData.length; i++) {
      for (let j = 0; j < this.lineChartData[i].data.length; j++) {
        this.lineChartData[i].data[j] = this.generateNumber(i);
      }
    }
    this.chart.update();
  }

  private generateNumber(i: number) {
    return Math.floor((Math.random() * (i < 2 ? 100 : 1000)) + 1);
  }

  // events
  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  getBgColorForProp(value: number) {
    if (value >= 0 && value <= 19) {
        return '#FF0000';
    }
    if (value >= 20 && value <= 39) {
        return '#FFA500';
    }
    if (value >= 40 && value <= 59) {
        return '#FFFF00';
    }
    if (value >= 60 && value <= 79) {
        return '#008000';
    }
    if (value >= 80 && value <= 100) {
        return '#3366cc';
    }

}
  private setChartValues(flag:boolean) {
    if(flag==true){
    this.lineChartColors = [];
    console.log("Pruebaaa = " + flag)
    if(this.charasteristics!=null){
    this.charasteristics.forEach(chara => {

        if (chara.name === 'Cumplimiento') {
            // this.lineChartDataCump = [];
              for(let i=0;i<this.lineChartDataGeneral.length;i++){
                if(this.lineChartDataGeneral[i].label== chara.properties[0].name){
                  this.lineChartDataGeneral[i].data[this.lineChartDataGeneral[i].data.length]=chara.properties[0].value;
                  console.log("values of Cuplim = " + chara.properties[0].name + "Data = " + this.lineChartDataGeneral[i].data[this.lineChartDataGeneral[i].data.length]);
                } if(this.lineChartDataGeneral[i].label== chara.properties[1].name){
                  this.lineChartDataGeneral[i].data[this.lineChartDataGeneral[i].data.length]=chara.properties[1].value;
                }
              }





            // chara.properties.forEach(prop => {
            //     this.lineChartDataCump.push(
            //         {backgroundColor: [this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value)]},
            //     );
            // });
        }
        if (chara.name === 'Confidencialidad') {
           // this.lineChartDataConf = [];

            for(let i=0;i<this.lineChartDataGeneral.length;i++){
              if(this.lineChartDataGeneral[i].label== chara.properties[0].name){
                this.lineChartDataGeneral[i].data[this.lineChartDataGeneral[i].data.length]=chara.properties[0].value;
              } if(this.lineChartDataGeneral[i].label== chara.properties[1].name){
                this.lineChartDataGeneral[i].data[this.lineChartDataGeneral[i].data.length]=(chara.properties[1].value);
              }
            }
          }

            // chara.properties.forEach(prop => {
            //     this.lineChartDataConf.push(
            //         {backgroundColor: [this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value)]},
            //     );
            // });

        if (chara.name === 'Trazabilidad') {
           // this.lineChartDataTraz = [];

            for(let i=0;i<this.lineChartDataGeneral.length;i++){
              if(this.lineChartDataGeneral[i].label== chara.properties[0].name){
                this.lineChartDataGeneral[i].data[this.lineChartDataGeneral[i].data.length]=(chara.properties[0].value);
              } if(this.lineChartDataGeneral[i].label== chara.properties[1].name){
                this.lineChartDataGeneral[i].data[this.lineChartDataGeneral[i].data.length]=(chara.properties[1].value);
              }
            }
          }

            // chara.properties.forEach(prop => {
            //     this.lineChartDataTraz.push(
            //         {backgroundColor: [this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value)]},
            //     );
            // });

        if (chara.name === 'Disponibilidad') {
           // this.lineChartDataDisp = [];

            for(let i=0;i<this.lineChartDataGeneral.length;i++){
              if(this.lineChartDataGeneral[i].label== chara.properties[0].name){
                this.lineChartDataGeneral[i].data[this.lineChartDataGeneral[i].data.length]=(chara.properties[0].value);
              } if(this.lineChartDataGeneral[i].label== chara.properties[1].name){
                this.lineChartDataGeneral[i].data[this.lineChartDataGeneral[i].data.length]=(chara.properties[1].value);
              }
              if(this.lineChartDataGeneral[i].label== chara.properties[2].name){
               this.lineChartDataGeneral[i].data[this.lineChartDataGeneral[i].data.length]=(chara.properties[2].value);
             }
            }
            // chara.properties.forEach(prop => {
            //     this.lineChartDataDisp.push(
            //         {backgroundColor: [this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value)]},
            //     );
            // });
        }
        if (chara.name === 'Recuperabilidad') {
            //this.lineChartDataRecup = [];

              for(let i=0;i<this.lineChartDataGeneral.length;i++){
                if(this.lineChartDataGeneral[i].label== chara.properties[0].name){
                  this.lineChartDataGeneral[i].data[this.lineChartDataGeneral[i].data.length]=(chara.properties[0].value);
                } if(this.lineChartDataGeneral[i].label== chara.properties[1].name){
                  this.lineChartDataGeneral[i].data[this.lineChartDataGeneral[i].data.length]=(chara.properties[1].value);
                }
                if(this.lineChartDataGeneral[i].label== chara.properties[2].name){
                 this.lineChartDataGeneral[i].data[this.lineChartDataGeneral[i].data.length]=(chara.properties[2].value);
               }
              }
        }

    });
  }

    this.radarChartData = [{data: [
            this.charasteristics[0].value,
            this.charasteristics[1].value,
            this.charasteristics[2].value,
            this.charasteristics[3].value,
            this.charasteristics[4].value,
        ], label: 'Valor de ciberseguridad'}];
      }
}

public radarChartData: ChartDataSets[] = [
  {data: [5, 3, 3, 5, 2], label: 'Valor de ciberseguridad'}
];


}
