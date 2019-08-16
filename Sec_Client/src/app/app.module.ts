import { BrowserModule } from '@angular/platform-browser';

// MODULOS
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import Swal from 'sweetalert2';

// COMPONENTES
import { AppComponent } from './app.component';
import { HeaderComponent } from './estructura_base/header/header.component';
import { FooterComponent} from './estructura_base/footer/footer.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { FormularioComponent } from './usuarios/formulario.component';
import { EmpresasComponent } from './empresas/empresas.component';
import { EvaluacionesComponent } from './evaluaciones/evaluaciones.component';
import { FormuEmpresasComponent } from './empresas/empresas_formulario.component';
import { InformacionComponent } from './empresas/informacion_empresa/informacion_empresa.component';
import { ProductosComponent } from './productos/productos.components';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { EvaluacionComponent } from './evaluaciones/evaluacion/evaluacion.component';
import { FormularioProductoComponent } from './productos/formularioproducto/formulario_producto.component';
import {EvaluacionesFormularioComponent} from './evaluaciones/evaluaciones_formulario.component';

// SERVICIOS
import { UsuarioService } from './usuarios/usuario.service';
import {EmpresaService} from './empresas/empresa.service';
import { AuthService } from './usuarios/auth.service';
import {EvaluacionService} from './evaluaciones/evaluaciones.service';
import {ProductoService} from './productos/producto.service';

// RUTAS Y OTROS COMPLEMENTOS
import { RouterModule, Routes} from '@angular/router';
import {AuthGuard} from './usuarios/guards/auth.guard';
import {RoleGuard} from './usuarios/guards/role.guard';
import { HistoricoComponent } from './evaluaciones/historico/historico.component';


// RUTAS
const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'usuarios', component: UsuariosComponent, canActivate: [AuthGuard]},
  {path: 'empresas', component: EmpresasComponent, canActivate: [AuthGuard]},
  {path: 'evaluaciones', component: EvaluacionesComponent},
  {path: 'info', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'usuarios/form', component: FormularioComponent, canActivate: [AuthGuard]},
  {path: 'usuarios/form/:id', component: FormularioComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'empresas/ver/:id', component: InformacionComponent},
  {path: 'empresas/form', component: FormuEmpresasComponent},
  {path: 'empresas/form/:id', component: FormuEmpresasComponent},
  {path: 'productos', component: ProductosComponent},
  {path: 'evaluaciones/resultados_generales/:id', component: EvaluacionComponent},
  {path: 'evaluaciones/historico/:id', component: HistoricoComponent},
  {path: 'evaluaciones/form', component: EvaluacionesFormularioComponent},
  {path: 'evaluaciones/form/:id', component: EvaluacionesFormularioComponent},
  {path: 'productos/form', component: FormularioProductoComponent},
  {path: 'productos/form/:id', component: FormularioProductoComponent},
];


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    UsuariosComponent,
    FormularioComponent,
    HomeComponent,
    LoginComponent,
    EmpresasComponent,
    EvaluacionesComponent,
    FormuEmpresasComponent,
    InformacionComponent,
    ProductosComponent,
    EvaluacionComponent,
    EvaluacionesFormularioComponent,
    HistoricoComponent,
    FormularioProductoComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ChartsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [UsuarioService,  AuthService, AuthGuard, RoleGuard, EmpresaService,  EvaluacionService, ProductoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
