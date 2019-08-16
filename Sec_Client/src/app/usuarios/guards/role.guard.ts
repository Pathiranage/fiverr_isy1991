import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import {AuthService} from '../auth.service';


@Injectable()
export class RoleGuard implements CanActivate {

    constructor(private authService: AuthService, private router: Router){
    }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

      let role= next.data['role'] as string;
      if(!this.authService.hasRole(role)){
        this.router.navigate(['/login']);
        return false;
      }


    return false;
  }
}
