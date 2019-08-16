import {Empresa} from '../empresas/empresa';
import {Version} from './version';

export class Producto{
   id: number;
   nombre: string;
   version: string;
   empresa: Empresa;
   versiones: Version[] = [];
}
