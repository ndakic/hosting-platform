import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../../services/authentication.service';


@Component({
  selector: 'app-navbar-registered-user',
  templateUrl: './navbar-registered-user.component.html',
  styleUrls: ['./navbar-registered-user.component.css']
})
export class NavbarRegisteredUserComponent implements OnInit {

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
  }


  onClickLogout(): void {
    this.authenticationService.logout();
    this.toastr.success('Logout successful!');
    this.router.navigate(['home']);
  }

  onTitleClick(): void {
    this.router.navigate(['']);
  }

  onClickChangePassword(): void {
    this.router.navigate(['change-password']);
  }

  onClickChangeBasicInfo(): void {
    this.router.navigate(['change-basic-info']);
  }

}
