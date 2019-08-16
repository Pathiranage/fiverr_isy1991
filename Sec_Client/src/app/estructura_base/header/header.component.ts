import { Component } from '@angular/core';
import {AuthService} from '../../usuarios/auth.service';
import {Router} from '@angular/router';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent {
 title: string = 'Data Security'

 constructor(private authService: AuthService, private router: Router){}

 logout():void{
   let username = this.authService.usuario.username;
   this.authService.logout();

   this.router.navigate(['/login']);
   window.location.reload();
 }
}
