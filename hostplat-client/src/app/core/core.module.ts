import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationService } from './services/authentication.service';
import { AppRoutingModule } from '../app-routing.module';
import { MaterialModule } from '../material/material.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MaterialModule,
    AppRoutingModule
  ],
  providers: [
    AuthenticationService,
  ],
  exports: [
  ]
})
export class CoreModule {
  constructor( @Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule has already been loaded. You should only import Core modules in the AppModule only.');
    }
  }
}
