import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { MaterialModule } from '../material/material.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AccountConfirmationComponent } from './account-confirmation/account-confirmation.component';
import { AppRoutingModule } from '../app-routing.module';



@NgModule({
  declarations: [RegisterComponent, LoginComponent, AccountConfirmationComponent],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    AppRoutingModule
  ],
  exports: [
    LoginComponent,
    RegisterComponent,
    AccountConfirmationComponent

  ]
})
export class AuthenticationModule { }
