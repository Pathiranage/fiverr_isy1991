import {Producto} from '../../productos/producto';
export class Eveproperties {
   id: number;
   createAt: string;
   version: string;
   fecha_evaluacion : string;
   valor_ciberseguridad: string;
   valor_cump: number;
   valor_conf: number;
   valor_traz: number;
   valor_disp: number;
   valor_recup: number;
   producto: Producto;
}