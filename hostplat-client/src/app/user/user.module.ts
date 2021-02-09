import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { ChangeBasicInfoComponent } from './change-basic-info/change-basic-info.component';




@NgModule({
  declarations: [ChangePasswordComponent, ChangeBasicInfoComponent],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule
  ],
  exports: [
    ChangePasswordComponent
  ]
})
export class UserModule { }
