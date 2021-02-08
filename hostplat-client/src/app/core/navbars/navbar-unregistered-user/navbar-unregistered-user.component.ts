import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar-unregistered-user',
  templateUrl: './navbar-unregistered-user.component.html',
  styleUrls: ['./navbar-unregistered-user.component.css']
})
export class NavbarUnregisteredUserComponent implements OnInit {

  constructor(
    private router: Router) {
  }

  ngOnInit(): void {
  }

  onTitleClick(): void {
    this.router.navigate(['']);
  }

  onClickLogin(): void {
    this.router.navigate(['login']);
  }

  onClickRegister(): void {
    this.router.navigate(['register']);
  }

}
