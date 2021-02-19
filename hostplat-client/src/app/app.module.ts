import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { AuthenticationModule } from './authentication/authentication.module';
import { CoreModule } from './core/core.module';
import { PagesModule } from './pages/pages.module';
import { ToastrModule } from 'ngx-toastr';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserModule } from './user/user.module';
import { Jwt } from './core/interceptors/jwt.interceptor';
import { ProjectModule} from './projects/project.module'; 
import { TaskModule } from './tasks/task.module';
import { MilestoneModule } from './milestones/milestone.module';
import { LabelModule} from './labels/label.module';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialModule,
    AuthenticationModule,
    CoreModule,
    PagesModule,
    UserModule,
    ProjectModule,
    TaskModule,
    MilestoneModule,
    LabelModule,
    ToastrModule.forRoot({
      progressBar: true,
      timeOut: 4000,
      closeButton: true,
      positionClass: 'toast-top-right',
      preventDuplicates: true
    }),
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: Jwt, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
