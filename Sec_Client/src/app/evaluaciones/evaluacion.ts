import {Producto} from '../productos/producto';
import {Macrocharacteristic} from './macrocharacteristic';
import {Usuario} from '../usuarios/usuario';

export class Evaluacion {
    id: number;
    createAt: string;

    version: string;
    fecha_evaluacion: string;
    valor_ciberseguridad: number;
    valor_cump: number;
    valor_conf: number;
    valor_traz: number;
    valor_disp: number;
    valor_recup: number;

    producto: Producto;
    usuarios: Usuario[] = [];
    macrocharacteristic: Macrocharacteristic;
}
