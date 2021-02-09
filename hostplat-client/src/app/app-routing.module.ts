import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { ForbiddenPageComponent } from './pages/forbidden-page/forbidden-page.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { NotFoundPageComponent } from './pages/not-found-page/not-found-page.component';
import { ChangeBasicInfoComponent } from './user/change-basic-info/change-basic-info.component';
import { ChangePasswordComponent } from './user/change-password/change-password.component';
import { RoleGuard } from './core/guards/role.guard';

const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'home', component: HomePageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'forbidden-page', component: ForbiddenPageComponent},
  {path: 'not-found-page', component: NotFoundPageComponent},
  {
    path: 'change-password',
    component: ChangePasswordComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },
  {
    path: 'change-basic-info',
    component: ChangeBasicInfoComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_USER'}
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
