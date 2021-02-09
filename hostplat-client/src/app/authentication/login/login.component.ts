import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Login } from 'src/app/models/login';
import { AuthenticationService } from 'src/app/core/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService,
              private toastr: ToastrService) {

    this.createForm();
  }

  ngOnInit() {
    if (this.authenticationService.isUserLoggedIn()) {
      this.router.navigate(['home']);
    }
  }

  private createForm(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onLogin(): void {
    const login: Login = {
      username: this.loginForm.value.username,
      password: this.loginForm.value.password
    };

    this.authenticationService.login(login).subscribe(data => {
      localStorage.setItem('user-id-key', data.id);
      localStorage.setItem('user-role-key', data.authorities[0]);
      localStorage.setItem('user-username-key', data.username);
      localStorage.setItem('user-token-key', data.token.accessToken);
      this.router.navigate(['home']);
    }, error => {
      this.toastr.warning(error.error.message, 'Warning');
    });
  }

  onClickRegister(): void {
    this.router.navigate(['register']);
  }

}
