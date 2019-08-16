import {Injectable} from '@angular/core';
import {Evaluacion} from './evaluacion';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {AuthService} from '../usuarios/auth.service';
import {ProductoService} from '../productos/producto.service';
import {Producto} from '../productos/producto';
import {Empresa} from '../empresas/empresa';
import {Charateristics} from './evaluacion/charateristics';
import {Usuario} from '../usuarios/usuario';

@Injectable()
export class EvaluacionService {

    private urlEndPoint = 'http://localhost:8080/api/evaluaciones';
    private urlExternalPoint = 'http://localhost:8080/api/productos';
    private urlExternalEmpresaPoint = 'http://localhost:8080/api/empresas';
    private urlExternalVesionsById = 'http://localhost:8080/api/versiones/product/${id}';
    private urlCharasteristicsByEvaluacionesId = 'http://localhost:8080/api/evaluaciones/${id}/charasterictics';
    private urlInformeId = 'http://localhost:8080/api/evaluaciones/${id}/informe';
    private urlAllUsers = 'http://localhost:8080/api/usuarios-by-role?role=${role}';

    private getProductId;
    private getevalId;

    private httpHeaders = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token')
    });


    constructor(private productoService: ProductoService, private http: HttpClient, private router: Router, private auth: AuthService) {
    }


    // Obtenemos las evaluaciones para ese usuario
    getEvaluaciones(): Observable<Evaluacion[]> {
        return this.http.get<Evaluacion[]>(this.urlEndPoint, {headers: this.httpHeaders});
        this.httpHeaders = null;
    }

    // Obtenemos los productos para crear la evaluaciones
    getProductos(): Observable<Producto[]> {
        return this.http.get<Producto[]>(this.urlExternalPoint);
    }

    // Obtenemos los empresas para la evaluaciones
    getEmpresas(): Observable<Empresa[]> {
        return this.http.get<Empresa[]>(this.urlExternalEmpresaPoint);
    }

    getAllVersionsByProductId(id: any): Observable<any[]> {
        return this.http.get<any[]>(this.urlExternalVesionsById.replace('${id}', id));
    }

    create(evaluacion: Evaluacion): Observable<Evaluacion> {
        return this.http.post<Evaluacion>(this.urlEndPoint, evaluacion, {headers: this.httpHeaders});
    }

    // Para acceder a la informacion de una evaluacion
    getEvaluacion(id): Observable<Evaluacion> {
        return this.http.get<Evaluacion>(`${this.urlEndPoint}/${id}`);
    }

    getCharasteristicsByEvaluacionesId(id: any): Observable<Charateristics[]> {
        return this.http.get<Charateristics[]>(this.urlCharasteristicsByEvaluacionesId.replace('${id}', id));
    }

    getInformebyId(id: any) {
        return this.http.get(this.urlInformeId.replace('${id}', id));
    }

    update(evaluacion: Evaluacion): Observable<Evaluacion> {
        return this.http.put<Evaluacion>(`${this.urlEndPoint}/${evaluacion.id}`, evaluacion, {headers: this.httpHeaders});
    }

    delete(id: number): Observable<Evaluacion> {
        return this.http.delete<Evaluacion>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders});
    }

    deletetodo(id: number): Observable<void> {
        return this.http.delete<void>(`${this.urlEndPoint}/${id}`);
    }

    findUsersByRole(role: string) {
        return this.http.get<Usuario[]>(this.urlAllUsers.replace('${role}', role), {headers: this.httpHeaders});
    }

    setProductId(value: number) {
        this.getProductId = value;

    }

    getterProductId(): number {
        return this.getProductId;
    }

    setevalId(value: number) {
        this.getevalId = value;

    }

    getterEvalId(): number {
        return this.getevalId;
    }

    private evaluaciones: Evaluacion[] = [
        {
            id: 1,
            createAt: '25-04-2019 12:04',
            version: '1.0',
            fecha_evaluacion: '25-04-2019 12:04',
            valor_ciberseguridad: 3,
            valor_cump: 5,
            valor_conf: 3,
            valor_traz: 3,
            valor_disp: 5,
            valor_recup: 2,
            producto: null,
            usuarios: null,
            macrocharacteristic: null

        },
        {
            id: 2,
            createAt: '25-04-2019 12:04',
            version: '1.1',
            fecha_evaluacion: '12-04-2019 10:10',
            valor_ciberseguridad: 4,
            valor_cump: 5,
            valor_conf: 5,
            valor_traz: 3,
            valor_disp: 5,
            valor_recup: 4,
            producto: null,
            usuarios: null,
            macrocharacteristic: null
        },
        {
            id: 3,
            createAt: '25-04-2019 12:04',
            version: '1.5.3',
            fecha_evaluacion: '09-04-2019 18:25',
            valor_ciberseguridad: 4,
            valor_cump: 4,
            valor_conf: 5,
            valor_traz: 3,
            valor_disp: 5,
            valor_recup: 4,
            producto: null,
            usuarios: null,
            macrocharacteristic: null
        },
        {
            id: 3,
            createAt: '25-04-2019 12:04',
            version: '0.9.1',
            fecha_evaluacion: '15-04-2019 11:32',
            valor_ciberseguridad: 2,
            valor_cump: 2,
            valor_conf: 3,
            valor_traz: 3,
            valor_disp: 5,
            valor_recup: 2,
            producto: null,
            usuarios: null,
            macrocharacteristic: null
        },
        {
            id: 4,
            createAt: '25-04-2019 12:04',
            version: '0.9.0',
            fecha_evaluacion: '05-04-2019 16:18',
            valor_ciberseguridad: 2,
            valor_cump: 2,
            valor_conf: 2,
            valor_traz: 3,
            valor_disp: 4,
            valor_recup: 2,
            producto: null,
            usuarios: null,
            macrocharacteristic: null
        },
    ];


}
