
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { AuthenticationService } from 'src/app/core/services/authentication.service';
import { UserRegistrationData } from 'src/app/models/user-registration-data';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  isUserInfoSent: boolean = false;
  registrationForm: FormGroup;

  constructor(private fb: FormBuilder,
              private authenticationService: AuthenticationService,
              private router: Router,
              private toastr: ToastrService) {
  }

  ngOnInit() {
    if (this.authenticationService.isUserLoggedIn()) {
      this.toastr.warning('Please logout if you want to create a new account.', 'Warning');
      this.router.navigate(['home']);
    }

    this.createForm();
  }

  private createForm(): void {
    this.registrationForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      repeatPassword: ['', Validators.required]
    });
  }

  onRegisterSubmit(): void {
    const password = this.registrationForm.controls.password.value;
    const repeatPassword = this.registrationForm.controls.repeatPassword.value;

    if (password !== repeatPassword) {
      this.toastr.warning('Passwords don\'t match', 'Warning');
      return;
    }

    const userInfo: UserRegistrationData = {
      username: this.registrationForm.controls.username.value,
      password,
      repeatPassword,
      email: this.registrationForm.controls.email.value,
      firstName: this.registrationForm.controls.firstName.value,
      lastName: this.registrationForm.controls.lastName.value,
    };

    this.authenticationService.addNewUser(userInfo).subscribe(data => {
      this.isUserInfoSent = true;
    }, error => {
      this.toastr.error('There was an error while adding your account. Try again later.');
    });
  }

  onClickSignIn(): void {
    this.router.navigate(['login']);
  }

}
