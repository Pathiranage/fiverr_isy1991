import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {EvaluacionService} from '../../evaluaciones/evaluaciones.service';
import {ChartDataSets, ChartOptions, ChartType} from 'chart.js';
import {Color, Label} from 'ng2-charts';
import {Evaluacion} from '../evaluacion';
import {Charateristics} from './charateristics';
import {DomSanitizer} from '@angular/platform-browser';
import swal from 'sweetalert2';

@Component({
    selector: 'app-evaluacion',
    templateUrl: './evaluacion.component.html',
    styleUrls: ['./evaluacion.component.css']
})
export class EvaluacionComponent {


    evaluaciones: Evaluacion[] = [];
    evaluacion: Evaluacion = new Evaluacion();
    charasteristics: Charateristics[] = [];
    selectedEval: any;
    version: any;
    nombre: any;
    date: any;
    value: any;
    productId: number;

    mostrarcump = false;
    mostrarconf = false;
    mostrartraz = false;
    mostrardisp = false;
    mostrarrecu = false;

    mostrarmenos1 = false;
    mostrarmenos2 = false;
    mostrarmenos3 = false;
    mostrarmenos4 = false;
    mostrarmenos5 = false;

    colorVar: any;

    // GRAFICA PARA EL RADAR GENERAL DE LAS CARACTERISTICAS

    public radarChartOptions: ChartOptions = {
        responsive: true,
        scales: {
            ticks: {
                min: 1,
                max: 5,
                stepSize: 1
            },
        }
    };


    public radarChartLabels: Label[] = ['CUMP', 'CONF', 'TRAZ', 'DISP', 'RECUP'];

    public radarChartData: ChartDataSets[] = [
        {data: [5, 3, 3, 5, 2], label: 'Valor de ciberseguridad'}
    ];
    public radarChartType: ChartType = 'radar';

    public radarChartColors = [
        {
            backgroundColor: [this.getBgColor(+this.value)],
        },
    ];


    // GRAFICA DE LINEAS  PARA LAS PROPIEDADES


    public barChartType: ChartType = 'bar';
    public barChartLegend = false;

// DATA SETS DE LAS CARACTERISTIICAS

// CUMPLIMIENTO
    public barChartLabelsCump: Label[] = ['CUMP_VAL', 'CUMP_TEC'];

    public barChartDataCump: ChartDataSets[] = [
        {data: [100, 0, 80], label: 'Cumplimiento normativo de valor y/o formato'},
        {data: [0, 100], label: 'Cumplimiento normativo debido a la tecnologia formato'},
    ];

    public barChartOptionsCump: ChartOptions = {
        responsive: true,
        // We use these empty structures as placeholders for dynamic theming.
        scales: {
            yAxes: [{
                ticks: {
                    min: 0,
                    max: 100
                }
            }],
        },
        plugins: {
            datalabels: {
                anchor: 'end',
                align: 'end',
                // backgroundColor: ['#3366cc','#3366cc'],
            }
        }
    };

    public barChartColorsCump: Color[] = [
        {
            backgroundColor: ['#3366cc', '#3366cc', '#3366cc', '#3366cc'],

        },
        {
            backgroundColor: ['#3366cc', '#3366cc', '#3366cc', '#3366cc0'],
        }
    ];

// RECUPERABILIDAD

    public barChartLabelsRec: Label[] = ['RAT_REC', 'BACK', 'REC_ARQ'];

    public barChartDataRec: ChartDataSets[] = [
        {data: [100, 0, 0], label: 'Ratio de recuperabilidad de datos'},
        {data: [0, 62.7, 0], label: 'Backup periódico'},
        {data: [0, 0, 0], label: 'Recuperabilidad de Arquitectura'},
    ];

    public barChartColorsRec: Color[] = [
        {
            backgroundColor: ['#3366cc', '#3366cc', '#3366cc', '#3366cc'],

        },
        {
            backgroundColor: ['#ffff00', '#ffff00', '#ffff00', '#ffff00'],
        },
        {
            backgroundColor: ['#ff3300', '#ff3300', '#ff3300', '#ff3300'],
        }
    ];

    public barChartOptionsRecu: ChartOptions = {
      responsive: true,
      // We use these empty structures as placeholders for dynamic theming.
      scales: {
          yAxes: [{
              ticks: {
                  min: 0,
                  max: 100
              }
          }],
      },
      plugins: {
          datalabels: {
              anchor: 'end',
              align: 'end',
              // backgroundColor: ['#3366cc','#3366cc'],
          }
      }
    };


// TRAZABILIDAD

    public barChartLabelsTraz: Label[] = ['TRAZ_DAT', 'TRAZ_VAL'];

    public barChartDataTraz: ChartDataSets[] = [
        {data: [100, 0], label: 'Trazabilidad del Acceso a los Datos'},
        {data: [0, 0], label: 'Trazabilidad de los Valores de Datos'},
    ];

    public barChartColorsTraz: Color[] = [
        {
            backgroundColor: ['#3366cc', '#3366cc', '#3366cc', '#3366cc'],

        },
        {
            backgroundColor: ['#ff3300', '#ff3300', '#ff3300', '#ff3300'],
        }
    ];
    public barChartOptionsTraza: ChartOptions = {
      responsive: true,
      // We use these empty structures as placeholders for dynamic theming.
      scales: {
          yAxes: [{
              ticks: {
                  min: 0,
                  max: 100
              }
          }],
      },
      plugins: {
          datalabels: {
              anchor: 'end',
              align: 'end',
              // backgroundColor: ['#3366cc','#3366cc'],
          }
      }
    };

// CONFIDENCIALIDAD
    public barChartLabelsConf: Label[] = ['ENC', 'NO_VUL'];

    public barChartDataConf: ChartDataSets[] = [
        {data: [100, 0], label: 'Uso de Encriptación'},
        {data: [0, 0], label: 'No Vulnerabilidad'},
    ];


    public barChartColorsConf: Color[] = [
        {
            backgroundColor: ['#3366cc', '#3366cc', '#3366cc', '#3366cc'],

        },
        {
            backgroundColor: ['#ff3300', '#ff3300', '#ff3300', '#ff3300'],
        }
    ];
    public barChartOptionsConf: ChartOptions = {
      responsive: true,
      // We use these empty structures as placeholders for dynamic theming.
      scales: {
          yAxes: [{
              ticks: {
                  min: 0,
                  max: 100
              }
          }],
      },
      plugins: {
          datalabels: {
              anchor: 'end',
              align: 'end',
              // backgroundColor: ['#3366cc','#3366cc'],
          }
      }
    };


// DISPONIBILIDAD


    public barChartOptionsDisp: ChartOptions = {
      responsive: true,
      // We use these empty structures as placeholders for dynamic theming.
      scales: {
          yAxes: [{
              ticks: {
                  min: 0,
                  max: 100
              }
          }],
      },
      plugins: {
          datalabels: {
              anchor: 'end',
              align: 'end',
              // backgroundColor: ['#3366cc','#3366cc'],
          }
      }
    };

    public barChartColorsDisp: Color[] = [
        {
            backgroundColor: ['#3366cc', '#3366cc', '#3366cc', '#3366cc'],

        },
        {
            backgroundColor: ['#3366cc', '#3366cc', '#3366cc', '#3366cc'],
        },
        {
            backgroundColor: ['#3366cc', '#3366cc', '#3366cc', '#3366cc'],
        }
    ];
    public barChartLabelsDisp: Label[] = ['RAT_DISP', 'PROB_DISP', 'DISP_ARQ'];

    public barChartDataDisp: ChartDataSets[] = [
        {data: [100, 0, 0], label: 'Ratio de Disponibilidad de Datos'},
        {data: [0, 100, 0], label: 'Probabilidad de Disponibilidad de Datos'},
        {data: [0, 0, 100], label: 'Disponibilidad de Elementos de Arquitectura'},
    ];

    constructor(private activatedRouter: ActivatedRoute, private _evaluacionService: EvaluacionService, private router: Router, private sanitizer: DomSanitizer) {
        this.activatedRouter.params.subscribe(params => {
            this.fetchData(params['id']);
        });
        this.productId = this._evaluacionService.getterProductId();
    }

    pid:number;
    private fetchData(id: any) {

      this._evaluacionService.setevalId(id);

        this._evaluacionService.getEvaluacion(id)
            .subscribe(value => {
                this.evaluacion = value;
                this.version = this.evaluacion.producto.version;
                this.nombre = this.evaluacion.producto.nombre;
                this._evaluacionService.setProductId(this.evaluacion.producto.id);
                this.date = this.evaluacion.createAt;
                this.value = this.evaluacion.macrocharacteristic.value;
                this.setRadioColorValue();
            });
            console.log("Pid = " +this.pid);
        this._evaluacionService.getEvaluaciones()
            .subscribe(res => {
                this.evaluaciones = res;
            });

        this._evaluacionService.getCharasteristicsByEvaluacionesId(id)
            .subscribe(value => {
                this.charasteristics = value;
                this.setChartValues();
            });
    }

    verHistoricoEvaluacion(index: number) {
        this.router.navigate(['evaluaciones/historico', index]);
    }

    verEvaluacion(index: number) {
        this.router.navigate(['evaluaciones/evaluacion', index]);
    }


// events
    public chartClicked({event, active}: { event: MouseEvent, active: {}[] }): void {
        console.log(event, active);
    }

    public chartHovered({event, active}: { event: MouseEvent, active: {}[] }): void {
        console.log(event, active);
    }

    // events
    public chartClickedb({event, active}: { event: MouseEvent, active: {}[] }): void {
        console.log(event, active);
    }

    public chartHoveredb({event, active}: { event: MouseEvent, active: {}[] }): void {
        console.log(event, active);
    }

    loadData() {
        this.fetchData(this.selectedEval);
    }


    private setRadioColorValue() {
        this.radarChartColors=[];
        this.radarChartColors.push(
            {backgroundColor: [this.getBgColor(this.value)]}
        )
    }


    private setChartValues() {
        this.barChartColorsCump = [];
        this.barChartColorsConf = [];
        this.barChartColorsTraz = [];
        this.barChartColorsDisp = [];
        this.barChartColorsRec = [];
        this.charasteristics.forEach(chara => {
            if (chara.name === 'Cumplimiento') {
                this.barChartDataCump = [];
                this.barChartDataCump.push(
                    {data: [chara.properties[0].value, 0], label: chara.properties[0].name},
                    {data: [0, chara.properties[1].value], label: chara.properties[1].name}
                );
                chara.properties.forEach(prop => {
                    this.barChartColorsCump.push(
                        {backgroundColor: [this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value)]},
                    );
                });
            }
            if (chara.name === 'Confidencialidad') {
                this.barChartDataConf = [];
                this.barChartDataConf.push(
                    {data: [chara.properties[0].value, 0], label: chara.properties[0].name},
                    {data: [0, chara.properties[1].value], label: chara.properties[1].name},
                );
                chara.properties.forEach(prop => {
                    this.barChartColorsConf.push(
                        {backgroundColor: [this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value)]},
                    );
                });
            }
            if (chara.name === 'Trazabilidad') {
                this.barChartDataTraz = [];
                this.barChartDataTraz.push(
                    {data: [chara.properties[0].value, 0], label: chara.properties[0].name},
                    {data: [0, chara.properties[1].value], label: chara.properties[1].name}
                );
                chara.properties.forEach(prop => {
                    this.barChartColorsTraz.push(
                        {backgroundColor: [this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value)]},
                    );
                });
            }
            if (chara.name === 'Disponibilidad') {
                this.barChartDataDisp = [];
                this.barChartDataDisp.push(
                    {data: [chara.properties[0].value, 0, 0], label: chara.properties[0].name},
                    {data: [0, chara.properties[1].value, 0], label: chara.properties[1].name},
                    {data: [0, 0, chara.properties[2].value], label: chara.properties[2].name}
                );
                chara.properties.forEach(prop => {
                    this.barChartColorsDisp.push(
                        {backgroundColor: [this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value)]},
                    );
                });
            }
            if (chara.name === 'Recuperabilidad') {
                this.barChartDataRec = [];
                this.barChartDataRec.push(
                    {data: [chara.properties[0].value, 0, 0], label: chara.properties[0].name},
                    {data: [0, chara.properties[1].value, 0], label: chara.properties[1].name},
                    {data: [0, 0, chara.properties[2].value], label: chara.properties[2].name}
                );
                chara.properties.forEach(prop => {
                    this.barChartColorsRec.push(
                        {backgroundColor: [this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value), this.getBgColorForProp(prop.value)]},
                    );
                });
            }
        });

        this.radarChartData = [{data: [
                this.charasteristics[0].value,
                this.charasteristics[1].value,
                this.charasteristics[2].value,
                this.charasteristics[3].value,
                this.charasteristics[4].value,
            ], label: 'Valor de ciberseguridad'}];
    }

    getBgColor(value: number) {
        switch (value) {
            case 0:
                return '#FF0000';
            case 1:
                return '#FF0000';
            case 2:
                return '#FFA500';
            case 3:
                return '#FFFF00';
            case 4:
                return '#008000';
            case 5:
                return '#3366cc';
        }

        this.colorVar = true;
    }

    showGraph(name: string) {
        switch (name) {
            case 'Cumplimiento':
                this.mostrarcump = !this.mostrarcump;
                break;
            case 'Confidencialidad':
                this.mostrarconf = !this.mostrarconf;
                break;
            case 'Trazabilidad':
                this.mostrartraz = !this.mostrartraz;
                break;
            case 'Disponibilidad':
                this.mostrardisp = !this.mostrardisp;
                break;
            case 'Recuperabilidad':
                this.mostrarrecu = !this.mostrarrecu;
                break;
        }
    }

    showProperties(name: string) {
        switch (name) {
            case 'Cumplimiento':
                return this.mostrarcump;
            case 'Confidencialidad':
                return this.mostrarconf;
            case 'Trazabilidad':
                return this.mostrartraz;
            case 'Disponibilidad':
                return this.mostrardisp;
            case 'Recuperabilidad':
                return this.mostrarrecu;
        }
    }

    getBgColorForProp(value: number) {
        if (value >= 0 && value <= 24) {
            return '#FF0000';
        }
        if (value >= 25 && value <= 49) {
            return '#FFA500';
        }
        if (value >= 50 && value <= 75) {
            return '#FFFF00';
        }
        if (value >= 76 && value <= 94) {
            return '#008000';
        }
        if (value >= 95 && value <= 100) {
            return '#3366cc';
        }
    }

    generarInforme(id: any){
      this._evaluacionService.getInformebyId(id)
          .subscribe(value => {
            console.log(id)
          });
            swal('Información', 'Generando informe...', 'info');
    }

    getTextColorCaracteristica(value: number){
      switch (value) {
          case 0:
              return '#FFF';
          case 1:
              return '#FFF';
          case 2:
              return '#000';
          case 3:
              return '#000';
          case 4:
              return '#FFF';
          case 5:
              return '#FFF';
      }
    }


    getTextColorPropiedad(value: number){
      if (value >= 0 && value <= 24) {
          return '#FFF';
      }
      if (value >= 25 && value <= 49) {
          return '#000';
      }
      if (value >= 50 && value <= 75) {
          return '#000';
      }
      if (value >= 76 && value <= 94) {
          return '#FFF';
      }
      if (value >= 95 && value <= 100) {
          return '#FFF';
      }
    }

}
