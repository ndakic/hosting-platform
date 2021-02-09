import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, CanActivate } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';


@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(
   public authenticationService: AuthenticationService,
   public router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles: string = route.data.expectedRoles;
    const token = this.authenticationService.getToken();
    const role = this.authenticationService.getRole();

    if (!token) {
      this.router.navigate(['/login']);
      return false;
    }

    const roles: string[] = expectedRoles.split('|');

    if (roles.indexOf(role) === -1) {
      this.router.navigate(['/forbidden-page']);
      return false;
    }
    return true;
}
}
