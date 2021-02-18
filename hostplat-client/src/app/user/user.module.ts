import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { ChangeBasicInfoComponent } from './change-basic-info/change-basic-info.component';
import { ChangeAvatarComponent } from './change-avatar/change-avatar.component';




@NgModule({
  declarations: [ChangePasswordComponent, ChangeBasicInfoComponent, ChangeAvatarComponent],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule
  ],
  exports: [
    ChangePasswordComponent,
    ChangeBasicInfoComponent,
    ChangeAvatarComponent
  ]
})
export class UserModule { }
