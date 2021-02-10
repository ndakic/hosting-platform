import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/services/user.service';
import { AuthenticationService } from 'src/app/core/services/authentication.service';
import { User } from 'src/app/models/user';
import { BasicInfo } from 'src/app/models/basic-info';

@Component({
  selector: 'app-change-basic-info',
  templateUrl: './change-basic-info.component.html',
  styleUrls: ['./change-basic-info.component.css']
})
export class ChangeBasicInfoComponent implements OnInit {
  user: User = {};
  basicInfoForm: FormGroup;

  constructor(private fb: FormBuilder,
              private userService: UserService,
              private toastr: ToastrService,
              private router: Router,
              private authService: AuthenticationService) {}

  ngOnInit(): void {
    this.getUserData();
    this.createBasicInfoForm();
  }

  private createBasicInfoForm(): void {
    this.basicInfoForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }
  private getUserData(): void {
    this.userService.getMyProfileData().subscribe(data => {
      this.user = {
        id: data.id,
        username: data.username,
        email: data.email,
        firstName: data.firstName,
        lastName: data.lastName,
        //imagePath: data.imagePath
      };

      this.addValuesToFormGroups();
    }, error => {
      this.toastr.error('There was an error while getting your profile data');
    });
  }

  private addValuesToFormGroups(): void {
    this.basicInfoForm.setValue({
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      email: this.user.email
    });
  }

  onClickSaveEdit(): void {
    const data: BasicInfo = {
      firstName: this.basicInfoForm.controls.firstName.value,
      lastName: this.basicInfoForm.controls.lastName.value,
      email: this.basicInfoForm.controls.email.value,
    };

    this.userService.editMyProfile(data).subscribe(data => {
      this.toastr.success('Your profile info has been successfully changed.');
    }, error => {
      this.toastr.error('There was an error. Your data will not be changed for now.');
    });
  }
  


}
