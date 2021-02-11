import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationService } from './services/authentication.service';
import { AppRoutingModule } from '../app-routing.module';
import { MaterialModule } from '../material/material.module';
import { NavbarUnregisteredUserComponent } from './navbars/navbar-unregistered-user/navbar-unregistered-user.component';
import { NavbarRegisteredUserComponent } from './navbars/navbar-registered-user/navbar-registered-user.component';
import { UserService } from './services/user.service';



@NgModule({
  declarations: [
    NavbarUnregisteredUserComponent,
    NavbarRegisteredUserComponent],
  imports: [
    CommonModule,
    MaterialModule,
    AppRoutingModule
  ],
  providers: [
    AuthenticationService,
    UserService
  ],
  exports: [
    NavbarUnregisteredUserComponent,
    NavbarRegisteredUserComponent
  ]
})
export class CoreModule {
  constructor( @Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule has already been loaded. You should only import Core modules in the AppModule only.');
    }
  }
}
