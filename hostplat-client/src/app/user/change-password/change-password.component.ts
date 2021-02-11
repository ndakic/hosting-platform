import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from 'src/app/core/services/authentication.service';
import { UserService } from 'src/app/core/services/user.service';
import { ChangePassword } from 'src/app/models/change-password';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  passwordForm: FormGroup;

  constructor(private fb: FormBuilder,
              private userService: UserService,
              private toastr: ToastrService,
              private router: Router,
              private authenticationService: AuthenticationService) {}

  ngOnInit(): void {
    this.createPasswordForm();
  }

  private createPasswordForm(): void {
    this.passwordForm = this.fb.group({
      oldPassword: ['', Validators.required],
      newPassword: ['', Validators.required]
    });
  }

  onClickEditPassword(): void {
    if (!this.passwordForm.valid) {
      this.toastr.warning('All fields must be filled out');
      return;
    }

    const oldPassword = this.passwordForm.controls.oldPassword.value;
    const newPassword = this.passwordForm.controls.newPassword.value;

    const passwords: ChangePassword = {
      oldPassword,
      newPassword
    };

    this.userService.changePassword(passwords).subscribe(data => {
      this.toastr.success('Your password has been changed. Please login again.');
      this.authenticationService.logout();
      this.router.navigate(['login']);
    }, error => {
      this.toastr.error('There was an error. Your password will remain the same');
    });
  }

}
