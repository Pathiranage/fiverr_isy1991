import {Producto} from '../productos/producto'

export class Empresa {
  id: number;
  nombre: string;
  direccion: string;
  telefono: number;
  cif:string;

  nombre_empleado: string;
  apellido_empleado: string;
  cargo_empleado: string;
  email_empleado: string;

  productos: Producto[] = [];
}
